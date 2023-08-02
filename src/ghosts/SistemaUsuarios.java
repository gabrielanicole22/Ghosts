/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

import java.util.ArrayList;

/**
 *
 * @author Gabriela Mejía
 */
public class SistemaUsuarios {
    
    private static SistemaUsuarios instancia; // Instancia única de SistemaUsuarios

    int usuariosHistoricos = 0;
    ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
    Usuario usuarioIniciado = null;

    // CONSTRUCTOR
    public SistemaUsuarios() {
    }

    public static SistemaUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new SistemaUsuarios();
        }
        return instancia;
    }

    public boolean esUsuarioUnico(String usuario) {
        // Es el primer usuario en registrarse
        if (usuariosHistoricos == 0) {
            return true;
        }

        return esUsuarioUnicoRecursivo(usuario, 0);
    }

    private boolean esUsuarioUnicoRecursivo(String usuario, int index) {
        if (index >= usuariosActivos.size()) {
            return true;
        }

        String usuarioActual = usuariosActivos.get(index).getUsuario();

        if (usuario.equals(usuarioActual)) {
            return false;
        }

        return esUsuarioUnicoRecursivo(usuario, index + 1);
    }

    public Usuario getUsuario(String username) {
        return getUsuarioRecursivo(username, 0);
    }

    private Usuario getUsuarioRecursivo(String username, int index) {
        if (index >= usuariosActivos.size()) {
            return null;
        }

        Usuario user = usuariosActivos.get(index);

        if (user.getUsuario().equals(username)) {
            return user;
        }

        return getUsuarioRecursivo(username, index + 1);
    }

    public boolean verificarEspaciosUsuario(String usuario) {
        return verificarEspaciosUsuarioRecursivo(usuario, 0);
    }

    private boolean verificarEspaciosUsuarioRecursivo(String usuario, int index) {
        if (index >= usuario.length()) {
            return true;
        }

        char charActual = usuario.charAt(index);

        if (charActual == ' ') {
            return false;
        }

        return verificarEspaciosUsuarioRecursivo(usuario, index + 1);
    }

    public void registrarUsuario(String usuario, String contrasena) {
        Usuario nuevoUsuario = new Usuario(usuario, contrasena);
        usuariosActivos.add(nuevoUsuario);
        // Agregar el usuario a los usuarios historicos.
        usuariosHistoricos++;
    }

    public Usuario[] getUsuariosActivos() {
        return usuariosActivos.toArray(new Usuario[0]);
    }

    public Usuario iniciarSesion(String usuario, String contrasena) {
        return iniciarSesionRecursivo(usuario, contrasena, 0);
    }

    private Usuario iniciarSesionRecursivo(String usuario, String contrasena, int index) {
        if (index >= usuariosActivos.size()) {
            return null;
        }

        Usuario usuarioActual = usuariosActivos.get(index);
        if (usuarioActual == null) {
            return iniciarSesionRecursivo(usuario, contrasena, index + 1);
        }

        if (usuarioActual.validarCredenciales(usuario, contrasena)) {
            // Guardar el usuario que esta actualmente iniciado en el manager.
            this.usuarioIniciado = usuarioActual;
            return usuarioActual;
        }

        return iniciarSesionRecursivo(usuario, contrasena, index + 1);
    }

    public void eliminarUsuario(Usuario usuario) {
        eliminarUsuarioRecursivo(usuario, 0);
    }

    private void eliminarUsuarioRecursivo(Usuario usuario, int index) {
        if (index >= usuariosActivos.size()) {
            return;
        }

        Usuario user = usuariosActivos.get(index);
        if (user.equals(usuario)) {
            usuariosActivos.remove(index);
            return;
        }

        eliminarUsuarioRecursivo(usuario, index + 1);
    }

    public void actualizarUsuario(Usuario usuarioActualizado) {
        actualizarUsuarioRecursivo(usuarioActualizado, 0);
    }

    private void actualizarUsuarioRecursivo(Usuario usuarioActualizado, int index) {
        if (index >= usuariosActivos.size()) {
            return;
        }

        Usuario usuario = usuariosActivos.get(index);
        // Buscar al usuario en base al nombre de usuario
        if (usuario.getUsuario().equals(usuarioActualizado.getUsuario())) {
            // Actualizar los datos del usuario
            usuariosActivos.set(index, usuarioActualizado); // Guardar el usuario actualizado en el array
            return;
        }

        actualizarUsuarioRecursivo(usuarioActualizado, index + 1);
    }

    /**
     * Retorna el usuario que tiene la sesion activa.
     *
     * @return
     */
    public Usuario getUsuarioActual() {
        return usuarioIniciado;
    }
}
