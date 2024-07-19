package com.example.cognicare.Listeners;

import com.example.cognicare.Models.InstructionResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}
