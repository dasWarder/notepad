package com.example.notepad.service.exception;

public class NoteNotFoundException extends Throwable {

    public NoteNotFoundException() {
    }

    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
