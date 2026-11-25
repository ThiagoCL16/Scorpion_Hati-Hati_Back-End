package com.scorpionTCC.demo.responses;

import com.scorpionTCC.demo.entity.Usuario;

public record LoginResponse(String msg, Usuario u) {
}
