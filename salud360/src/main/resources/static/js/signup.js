// ============================================
// signup.js
// ============================================

document.addEventListener('DOMContentLoaded', function() {
    const registerBtn = document.querySelector('.register-btn');
    const inputs = document.querySelectorAll('input');
    const nombreInput = inputs[0];
    const emailInput = inputs[1];
    const passwordInput = inputs[2];
    const confirmPasswordInput = inputs[3];
    const loginLink = document.querySelector('.login-link a');

    if (loginLink) {
        loginLink.href = 'login.html';
    }

    registerBtn.addEventListener('click', async function(e) {
        e.preventDefault();

        const nombre = nombreInput.value.trim();
        const correo = emailInput.value.trim();
        const contrasenia = passwordInput.value;
        const confirmarContrasenia = confirmPasswordInput.value;

        if (!API.Validaciones.noEstaVacio(nombre)) {
            API.UI.mostrarError('Por favor, ingresa tu nombre completo');
            return;
        }

        if (!API.Validaciones.esEmailValido(correo)) {
            API.UI.mostrarError('Por favor, ingresa un correo electrónico válido');
            return;
        }

        if (!API.Validaciones.esContraseniaValida(contrasenia)) {
            API.UI.mostrarError('La contraseña debe tener al menos 6 caracteres');
            return;
        }

        if (contrasenia !== confirmarContrasenia) {
            API.UI.mostrarError('Las contraseñas no coinciden');
            return;
        }

        const textoOriginalBtn = registerBtn.innerHTML;
        API.UI.mostrarCargando(registerBtn);

        try {
            try {
                await API.Paciente.buscarPorCorreo(correo);
                API.UI.mostrarError('Este correo ya está registrado. Por favor, inicia sesión.');
                API.UI.ocultarCargando(registerBtn, textoOriginalBtn);
                return;
            } catch (error) {
                // Si no encuentra el usuario, continuar con el registro
            }

            const pacienteData = {
                nombre: nombre,
                correo: correo,
                contrasenia: contrasenia
            };

            const pacienteCreado = await API.Paciente.registrar(pacienteData);

            API.UI.mostrarExito('¡Registro exitoso! Serás redirigido al inicio de sesión.');

            nombreInput.value = '';
            emailInput.value = '';
            passwordInput.value = '';
            confirmPasswordInput.value = '';

            setTimeout(() => {
                window.location.href = 'login.html';
            }, 2000);

        } catch (error) {
            console.error('Error al registrar:', error);
            API.UI.mostrarError('Error al crear la cuenta. Por favor, intenta nuevamente.');
            API.UI.ocultarCargando(registerBtn, textoOriginalBtn);
        }
    });
});