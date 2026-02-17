package naderdeghaili.u5w3d2hw.payloads;


import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timeStamp) {
}
