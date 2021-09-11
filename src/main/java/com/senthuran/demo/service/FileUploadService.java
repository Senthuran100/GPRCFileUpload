package com.senthuran.demo.service;

import com.google.protobuf.ByteString;
import com.senthuran.GRPCFileUpload.FileUploadRequest;
import com.senthuran.GRPCFileUpload.FileUploadResponse;
import com.senthuran.GRPCFileUpload.FileUploadServiceGrpc;
import com.senthuran.GRPCFileUpload.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@GRpcService
public class FileUploadService extends FileUploadServiceGrpc.FileUploadServiceImplBase {
    private static final Path SERVER_BASE_PATH = Paths.get("src/test/resources/output");

    @Override
    public StreamObserver<FileUploadRequest> upload(StreamObserver<FileUploadResponse> responseObserver) {
        return new StreamObserver<FileUploadRequest>() {
            // upload context variables
            OutputStream writer;
            Status status = Status.IN_PROGRESS;

            @Override
            public void onNext(FileUploadRequest fileUploadRequest) {
                try{
                    if(fileUploadRequest.hasMetadata()){
                        writer = getFilePath(fileUploadRequest);
                    }else{
                        writeFile(writer, fileUploadRequest.getFile().getContent());
                    }
                }catch (IOException e){
                    this.onError(e);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                status = Status.FAILED;
                this.onCompleted();
            }

            @Override
            public void onCompleted() {
                closeFile(writer);
                status = Status.IN_PROGRESS.equals(status) ? Status.SUCCESS : status;
                FileUploadResponse response = FileUploadResponse.newBuilder()
                        .setStatus(status)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    private OutputStream getFilePath(FileUploadRequest request) throws IOException {
        String fileName = request.getMetadata().getName() + "." + request.getMetadata().getType();
        return Files.newOutputStream(SERVER_BASE_PATH.resolve(fileName), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private void writeFile(OutputStream writer, ByteString content) throws IOException {
        writer.write(content.toByteArray());
        writer.flush();
    }

    private void closeFile(OutputStream writer){
        try {
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
