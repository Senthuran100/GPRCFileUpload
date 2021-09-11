package com.senthuran.GRPCFileUpload;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: fileUpload.proto")
public final class FileUploadServiceGrpc {

  private FileUploadServiceGrpc() {}

  public static final String SERVICE_NAME = "FileUploadService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.senthuran.GRPCFileUpload.FileUploadRequest,
      com.senthuran.GRPCFileUpload.FileUploadResponse> getUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "upload",
      requestType = com.senthuran.GRPCFileUpload.FileUploadRequest.class,
      responseType = com.senthuran.GRPCFileUpload.FileUploadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.senthuran.GRPCFileUpload.FileUploadRequest,
      com.senthuran.GRPCFileUpload.FileUploadResponse> getUploadMethod() {
    io.grpc.MethodDescriptor<com.senthuran.GRPCFileUpload.FileUploadRequest, com.senthuran.GRPCFileUpload.FileUploadResponse> getUploadMethod;
    if ((getUploadMethod = FileUploadServiceGrpc.getUploadMethod) == null) {
      synchronized (FileUploadServiceGrpc.class) {
        if ((getUploadMethod = FileUploadServiceGrpc.getUploadMethod) == null) {
          FileUploadServiceGrpc.getUploadMethod = getUploadMethod = 
              io.grpc.MethodDescriptor.<com.senthuran.GRPCFileUpload.FileUploadRequest, com.senthuran.GRPCFileUpload.FileUploadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "FileUploadService", "upload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.senthuran.GRPCFileUpload.FileUploadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.senthuran.GRPCFileUpload.FileUploadResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FileUploadServiceMethodDescriptorSupplier("upload"))
                  .build();
          }
        }
     }
     return getUploadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileUploadServiceStub newStub(io.grpc.Channel channel) {
    return new FileUploadServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileUploadServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FileUploadServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileUploadServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FileUploadServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FileUploadServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.senthuran.GRPCFileUpload.FileUploadRequest> upload(
        io.grpc.stub.StreamObserver<com.senthuran.GRPCFileUpload.FileUploadResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getUploadMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.senthuran.GRPCFileUpload.FileUploadRequest,
                com.senthuran.GRPCFileUpload.FileUploadResponse>(
                  this, METHODID_UPLOAD)))
          .build();
    }
  }

  /**
   */
  public static final class FileUploadServiceStub extends io.grpc.stub.AbstractStub<FileUploadServiceStub> {
    private FileUploadServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileUploadServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileUploadServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileUploadServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.senthuran.GRPCFileUpload.FileUploadRequest> upload(
        io.grpc.stub.StreamObserver<com.senthuran.GRPCFileUpload.FileUploadResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class FileUploadServiceBlockingStub extends io.grpc.stub.AbstractStub<FileUploadServiceBlockingStub> {
    private FileUploadServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileUploadServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileUploadServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileUploadServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class FileUploadServiceFutureStub extends io.grpc.stub.AbstractStub<FileUploadServiceFutureStub> {
    private FileUploadServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileUploadServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileUploadServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileUploadServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_UPLOAD = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FileUploadServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FileUploadServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.upload(
              (io.grpc.stub.StreamObserver<com.senthuran.GRPCFileUpload.FileUploadResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FileUploadServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileUploadServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.senthuran.GRPCFileUpload.FileUpload.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileUploadService");
    }
  }

  private static final class FileUploadServiceFileDescriptorSupplier
      extends FileUploadServiceBaseDescriptorSupplier {
    FileUploadServiceFileDescriptorSupplier() {}
  }

  private static final class FileUploadServiceMethodDescriptorSupplier
      extends FileUploadServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FileUploadServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FileUploadServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileUploadServiceFileDescriptorSupplier())
              .addMethod(getUploadMethod())
              .build();
        }
      }
    }
    return result;
  }
}
