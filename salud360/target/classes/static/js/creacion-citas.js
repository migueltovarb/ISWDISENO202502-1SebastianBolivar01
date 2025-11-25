// ============================================
// creacion-citas.js - Funcionalidad para crear citas
// ============================================

let medicoSeleccionado = null;
let fechaSeleccionada = null;
let horaSeleccionada = null;

document.addEventListener('DOMContentLoaded', function() {
    // Verificar autenticación
    API.Session.requiereAutenticacion();
    
    const usuario = API.Session.obtenerUsuario();
    
    // Actualizar información del usuario en el header
    const userInfoElement = document.querySelector('.user-info');
    if (userInfoElement) {
        userInfoElement.innerHTML = `
            <span>👤 ${usuario.nombre}</span>
            <span>|</span>
            <span>📧 ${usuario.correo}</span>
        `;
    }

    // Cargar médicos
    cargarMedicos();

    // Configurar eventos
    configurarEventos();
});

async function cargarMedicos() {
    try {
        const medicos = await API.Medico.obtenerTodos();
        
        if (medicos.length > 0) {
            medicoSeleccionado = medicos[0];
            mostrarMedicoSeleccionado(medicoSeleccionado);
        } else {
            API.UI.mostrarError('No hay médicos disponibles. Contacta al administrador.');
        }
    } catch (error) {
        console.error('Error al cargar médicos:', error);
        API.UI.mostrarError('Error al cargar la lista de médicos');
    }
}

function mostrarMedicoSeleccionado(medico) {
    const doctorCard = document.querySelector('.doctor-card');
    
    if (doctorCard) {
        doctorCard.innerHTML = `
            <div class="doctor-info">
                <div class="doctor-avatar">👨‍⚕️</div>
                <div class="doctor-details">
                    <h3>${medico.nombre}</h3>
                    <p>${medico.especialidad}</p>
                    <p style="font-size: 0.85rem; color: #999;">Médico disponible</p>
                </div>
            </div>
            <div class="availability">
                <div class="availability-label">DISPONIBILIDAD:</div>
                <span class="availability-status">✓ Disponible</span>
            </div>
        `;
    }
}

function configurarEventos() {
    // Selector de especialidad
    const especialidadSelect = document.getElementById('especialidad');
    if (especialidadSelect) {
        especialidadSelect.addEventListener('change', async function() {
            const especialidad = this.value;
            
            if (especialidad) {
                try {
                    const medicos = await API.Medico.buscarPorEspecialidad(especialidad);
                    
                    if (medicos.length > 0) {
                        medicoSeleccionado = medicos[0];
                        mostrarMedicoSeleccionado(medicoSeleccionado);
                    } else {
                        API.UI.mostrarError('No hay médicos disponibles para esta especialidad');
                    }
                } catch (error) {
                    console.error('Error al buscar médicos:', error);
                }
            }
        });
    }

    // Interactividad del calendario
    document.querySelectorAll('.day:not(.disabled)').forEach(day => {
        day.addEventListener('click', function() {
            document.querySelectorAll('.day').forEach(d => d.classList.remove('selected'));
            this.classList.add('selected');
            
            // Obtener la fecha seleccionada
            const mesActual = 10; // Noviembre (0-indexed)
            const anioActual = 2025;
            const diaSeleccionado = parseInt(this.textContent);
            
            fechaSeleccionada = new Date(anioActual, mesActual, diaSeleccionado);
            console.log('Fecha seleccionada:', fechaSeleccionada);
        });
    });

    // Interactividad de los horarios
    document.querySelectorAll('.time-slot:not(.disabled)').forEach(slot => {
        slot.addEventListener('click', function() {
            document.querySelectorAll('.time-slot').forEach(s => s.classList.remove('selected'));
            this.classList.add('selected');
            horaSeleccionada = this.textContent.trim();
            console.log('Hora seleccionada:', horaSeleccionada);
        });
    });

    // Botón de confirmar cita
    const btnConfirmar = document.querySelector('.btn-primary');
    if (btnConfirmar) {
        btnConfirmar.addEventListener('click', confirmarCita);
    }

    // Botón cancelar
    const btnCancelar = document.querySelector('.btn-secondary');
    if (btnCancelar) {
        btnCancelar.addEventListener('click', function() {
            if (API.UI.confirmar('¿Deseas cancelar la creación de la cita?')) {
                window.location.href = 'modificar-cita.html';
            }
        });
    }
}

async function confirmarCita() {
    const usuario = API.Session.obtenerUsuario();
    const tipoConsultaSelect = document.getElementById('tipo-consulta');
    const motivoTextarea = document.getElementById('motivo');
    
    if (!tipoConsultaSelect || !motivoTextarea) {
        API.UI.mostrarError('Error al leer los datos del formulario');
        return;
    }
    
    const tipoConsulta = tipoConsultaSelect.value;
    const motivo = motivoTextarea.value.trim();

    // Validaciones
    if (!medicoSeleccionado) {
        API.UI.mostrarError('Por favor, selecciona un médico');
        return;
    }

    if (!fechaSeleccionada) {
        API.UI.mostrarError('Por favor, selecciona una fecha');
        return;
    }

    if (!horaSeleccionada) {
        API.UI.mostrarError('Por favor, selecciona una hora');
        return;
    }

    if (!API.Validaciones.noEstaVacio(motivo)) {
        API.UI.mostrarError('Por favor, describe el motivo de la consulta');
        return;
    }

    const btnConfirmar = document.querySelector('.btn-primary');
    const textoOriginal = btnConfirmar.innerHTML;
    API.UI.mostrarCargando(btnConfirmar);

    try {
        // Crear objeto cita
        const citaData = {
            pacienteId: usuario.id,
            medicoId: medicoSeleccionado.id,
            fecha: API.Formato.fechaAISO(fechaSeleccionada),
            hora: horaSeleccionada + ':00',
            tipo: tipoConsulta === 'presencial' ? 'Consulta Presencial' : 
                  tipoConsulta === 'virtual' ? 'Consulta Virtual' : 
                  'Consulta de Urgencia'
        };

        console.log('Creando cita:', citaData);

        const citaCreada = await API.Cita.crear(citaData);

        console.log('Cita creada:', citaCreada);

        // Mostrar mensaje de éxito
        const confirmation = document.getElementById('confirmation');
        if (confirmation) {
            confirmation.innerHTML = `
                <h3>✓ ¡Cita agendada exitosamente!</h3>
                <p><strong>${medicoSeleccionado.nombre}</strong> - ${medicoSeleccionado.especialidad}</p>
                <p>📅 ${API.Formato.formatearFecha(fechaSeleccionada)} a las ${horaSeleccionada}</p>
                <p>📍 ${citaData.tipo}</p>
                <p style="margin-top: 8px; font-size: 0.9rem;">Se ha enviado una confirmación a tu correo electrónico.</p>
            `;
            confirmation.classList.add('show');
            window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
        }

        // Limpiar formulario después de 3 segundos y redirigir
        setTimeout(() => {
            window.location.href = 'modificar-cita.html';
        }, 3000);

    } catch (error) {
        console.error('Error al crear cita:', error);
        API.UI.mostrarError('Error al agendar la cita. Por favor, intenta nuevamente.');
        API.UI.ocultarCargando(btnConfirmar, textoOriginal);
    }
}