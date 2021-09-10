package com.senthuran.demo.controllers;

import com.senthuran.GRPCFileUpload.PingPongServiceGrpc;
import com.senthuran.GRPCFileUpload.Request;
import com.senthuran.GRPCFileUpload.Response;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
public class PingPongGrpcController extends PingPongServiceGrpc.PingPongServiceImplBase {

    @Override
    public void ping(Request request, StreamObserver<Response> responseObserver) {
        log.info("Payload Received : " + request.getPayload());
        Response response = Response.newBuilder().setMessage("PONG").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
