// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fileUpload.proto

package com.senthuran.GRPCFileUpload;

public interface FileUploadRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:FileUploadRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.MetaData metadata = 1;</code>
   */
  boolean hasMetadata();
  /**
   * <code>.MetaData metadata = 1;</code>
   */
  com.senthuran.GRPCFileUpload.MetaData getMetadata();
  /**
   * <code>.MetaData metadata = 1;</code>
   */
  com.senthuran.GRPCFileUpload.MetaDataOrBuilder getMetadataOrBuilder();

  /**
   * <code>.File file = 2;</code>
   */
  boolean hasFile();
  /**
   * <code>.File file = 2;</code>
   */
  com.senthuran.GRPCFileUpload.File getFile();
  /**
   * <code>.File file = 2;</code>
   */
  com.senthuran.GRPCFileUpload.FileOrBuilder getFileOrBuilder();

  public com.senthuran.GRPCFileUpload.FileUploadRequest.RequestCase getRequestCase();
}