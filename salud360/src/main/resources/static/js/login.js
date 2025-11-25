// ============================================
// login.js
// ============================================

document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const loginBtn = document.querySelector('.login-btn');

    form.addEventListener('submit', async function(e) {
        e.preventDefault();

        const email = emailInput.value.trim();
        const password = passwordInput.value;

        if (!API.Validaciones.noEstaVacio(email) || !API.Validaciones.noEstaVacio(password)) {
            API.UI.mostrarError('Por favor, completa todos los campos');
            return;
        }

        if (!API.Validaciones.esEmailValido(email)) {
            API.UI.mostrarError('Por favor, ingresa un correo electrónico válido');
            return;
        }

        const textoOriginalBtn = loginBtn.innerHTML;
        API.UI.mostrarCargando(loginBtn);

        try {
            const paciente = await API.Paciente.buscarPorCorreo(email);

            if (paciente && paciente.contrasenia === password) {
                API.Session.guardarUsuario({
                    id: paciente.id,
                    nombre: paciente.nombre,
                    correo: paciente.correo,
                    tipo: 'paciente'
                });

                API.UI.mostrarExito('¡Bienvenido ' + paciente.nombre + '!');
                
                setTimeout(() => {
                    window.location.href = 'creacion-citas.html';
                }, 1000);
            } else {
                API.UI.mostrarError('Correo o contraseña incorrectos');
                API.UI.ocultarCargando(loginBtn, textoOriginalBtn);
            }
        } catch (error) {
            console.error('Error al iniciar sesión:', error);
            API.UI.mostrarError('Correo o contraseña incorrectos. Si no tienes cuenta, regístrate primero.');
            API.UI.ocultarCargando(loginBtn, textoOriginalBtn);
        }
    });

    const forgotPasswordLink = document.querySelector('.forgot-password a');
    if (forgotPasswordLink) {
        forgotPasswordLink.addEventListener('click', function(e) {
            e.preventDefault();
            const email = prompt('Ingresa tu correo electrónico:');
            if (email && API.Validaciones.esEmailValido(email)) {
                API.UI.mostrarExito('Se ha enviado un enlace de recuperación a tu correo');
            } else if (email) {
                API.UI.mostrarError('Por favor, ingresa un correo válido');
            }
        });
    }
});