package services;

import io.grpc.stub.StreamObserver;
import stub.SalutationGrpc;
import stub.TpStream.*;

public class MessageService extends SalutationGrpc.SalutationImplBase {
     @Override
        public StreamObserver<SalutRequest> message(StreamObserver<SalutResponse> responseObserver) {
            return new StreamObserver<>() {
                StringBuilder names = new StringBuilder();

                @Override
                public void onNext(SalutRequest request) {
                    names.append(request.getName()).append(", ");
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Erreur reçue : " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    String responseMessage = "Salut à tous : " + names.toString();
                    SalutResponse response = SalutResponse.newBuilder()
                            .setMessage(responseMessage)
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };
        }
}
