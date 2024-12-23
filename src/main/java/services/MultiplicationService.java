package services;

import io.grpc.stub.StreamObserver;
import stub.MultiplicationGrpc;
import stub.TpStream;

public class MultiplicationService extends MultiplicationGrpc.MultiplicationImplBase {
    @Override
    public void getMultiplicationTable(TpStream.MultiplicationRequest request, StreamObserver<TpStream.MultiplicationResponse> responseObserver) {
        int nombre = request.getNombre();
        int limite = request.getLimite();
        for (int i = 1; i <= limite; i++) {
            String result = nombre + " x " + i + " = " + (nombre * i);
            TpStream.MultiplicationResponse response = TpStream.MultiplicationResponse.newBuilder()
                    .setResultat(result)
                    .build();
// Envoyer le message au client
            responseObserver.onNext(response);
        }

// Terminer le streaming
        responseObserver.onCompleted();

    }

}
