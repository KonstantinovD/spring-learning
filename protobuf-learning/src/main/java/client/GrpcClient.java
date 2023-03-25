package client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import protobuf_generated.HelloRequest;
import protobuf_generated.HelloResponse;
import protobuf_generated.HelloServiceGrpc;

import java.util.concurrent.TimeUnit;

public class GrpcClient {

  private final HelloServiceGrpc.HelloServiceBlockingStub blockingStub;

  public GrpcClient(Channel channel) {
    blockingStub = HelloServiceGrpc.newBlockingStub(channel);
  }

  public void makeHelloRequest(String firstName, String lastName) {
    HelloRequest request = HelloRequest.newBuilder()
        .setFirstName(firstName)
        .setLastName(lastName)
        .build();
    System.out.println("Sending to server: " + request);
    HelloResponse helloResponse;
    try {
      helloResponse = blockingStub.hello(request);
    } catch (StatusRuntimeException e) {
      System.out.println("RPC failed: " + e.getStatus());
      return;
    }
    String message = "Response received from server:\n" + helloResponse;
    System.out.println(message);
  }

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();

    try {
      GrpcClient client = new GrpcClient(channel);
      client.makeHelloRequest("Baeldung", "gRPC");
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }

}
