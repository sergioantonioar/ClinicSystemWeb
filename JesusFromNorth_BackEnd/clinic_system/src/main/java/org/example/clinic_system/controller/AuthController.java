package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AuthResponse;
import org.example.clinic_system.dto.responseDTO.LoginDTO;
import org.example.clinic_system.service.User.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints para login y verificación de token")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "Login de usuario", description = "Autentica al usuario con username y password y devuelve un token JWT junto con la información del usuario (rol: ADMIN o DOCTOR).")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login exitoso", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
      @ApiResponse(responseCode = "400", description = "Credenciales inválidas o error en login", content = @Content(schema = @Schema(implementation = String.class)))
  })
  @PostMapping("/login")
  public ResponseEntity<?> login(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de login con username y password", required = true, content = @Content(schema = @Schema(implementation = LoginDTO.class))) @RequestBody LoginDTO loginDTO) {
    try {
      AuthResponse authResponse = authService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
      return ResponseEntity.ok(authResponse);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
  }

  @Operation(summary = "Verificar token JWT", description = "Endpoint protegido para probar si el token JWT enviado en la cabecera es válido. Retorna 'permitido' si el token es correcto.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Token válido", content = @Content(schema = @Schema(implementation = String.class))),
      @ApiResponse(responseCode = "401", description = "Token inválido o no autorizado", content = @Content)
  })
  @SecurityRequirement(name = "bearerAuth") // Para indicar que se requiere token
  @GetMapping("/check")
  public ResponseEntity<?> check() {
    return ResponseEntity.ok().body("permitido");
  }
}
