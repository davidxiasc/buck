// @generated
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/com/facebook/buck/remoteexecution/proto/metadata.proto

package com.facebook.buck.remoteexecution.proto;

@javax.annotation.Generated(value="protoc", comments="annotations:WorkerInfoOrBuilder.java.pb.meta")
public interface WorkerInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:facebook.remote_execution.WorkerInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string hostname = 1;</code>
   */
  java.lang.String getHostname();
  /**
   * <code>string hostname = 1;</code>
   */
  com.google.protobuf.ByteString
      getHostnameBytes();

  /**
   * <pre>
   * Absolute path in the FS where the execution is running.
   * </pre>
   *
   * <code>string execution_path = 2;</code>
   */
  java.lang.String getExecutionPath();
  /**
   * <pre>
   * Absolute path in the FS where the execution is running.
   * </pre>
   *
   * <code>string execution_path = 2;</code>
   */
  com.google.protobuf.ByteString
      getExecutionPathBytes();
}
