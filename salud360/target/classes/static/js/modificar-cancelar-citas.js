// ============================================
// modificar-cancelar-citas.js
// Para modificar-cita.html y cancelar-cita.html
// ============================================

let citasDelPaciente = [];
let citaActual = null;

// ============================================
// FUNCIONES PARA MODIFICAR-CITA.HTML
// ============================================

function inicializarModificarCita() {
    API.Session.requiereAutenticacion();
    
    const usuario = API.Session.obtenerUsuario();
    
    // Actualizar información del usuario
    const userInfoElement = document.querySelector('.user-info');
    if (userInfoElement) {
        userInfoElement.innerHTML = `
            <span>👤 ${usuario.nombre}</span>
            <span>|</span>
            <span>📧 ${usuario.correo}</span>
        `;
    }

    // Cargar citas del paciente
    cargarCitasPaciente(usuario.id);
}

async function cargarCitasPaciente(pacienteId) {
    try {
        citasDelPaciente = await API.Cita.obtenerFuturasPaciente(pacienteId);
        
        mostrarCitas(citasDelPaciente);
    } catch (error) {
        console.error('Error al cargar citas:', error);
        API.UI.mostrarError('Error al cargar tus citas');
    }
}

function mostrarCitas(citas) {
    const appointmentsList = document.querySelector('.appointments-list');
    
    if (!appointmentsList) return;
    
    if (citas.length === 0) {
        appointmentsList.innerHTML = '<p style="text-align: center; color: #999; padding: 40px;">No tienes citas programadas.</p>';
        return;
    }
    
    appointmentsList.innerHTML = '';
    
    citas.forEach(cita => {
        const citaElement = crearElementoCita(cita);
        appointmentsList.appendChild(citaElement);
    });
}

function crearElementoCita(cita) {
    const div = document.createElement('div');
    div.className = 'appointment-card';
    
    const fecha = new Date(cita.fecha);
    const opciones = { weekday: 'long', day: 'numeric', month: 'short', year: 'numeric' };
    const fechaFormateada = fecha.toLocaleDateString('es-ES', opciones);
    
    div.innerHTML = `
        <div class="appointment-icon">👨‍⚕️</div>
        <div class="appointment-info">
            <div class="appointment-doctor">Médico ID: ${cita.medico?.id || 'N/A'}</div>
            <div class="appointment-specialty">${cita.tipo || 'Consulta'}</div>
            <div class="appointment-datetime">
                <span>📅 ${fechaFormateada}</span>
                <span>🕐 ${cita.hora}</span>
            </div>
            <span class="appointment-type">${cita.tipo}</span>
        </div>
        <div class="appointment-actions">
            <button class="btn-action btn-edit" onclick="abrirModalModificar(${cita.id})">✏️ Modificar</button>
            <button class="btn-action btn-cancel" onclick="confirmarCancelacionDirecta(${cita.id})">🗑️ Cancelar</button>
        </div>
    `;
    
    return div;
}

function abrirModalModificar(citaId) {
    citaActual = citasDelPaciente.find(c => c.id === citaId);
    
    if (!citaActual) {
        API.UI.mostrarError('Cita no encontrada');
        return;
    }
    
    // Abrir modal
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.classList.add('show');
        document.body.style.overflow = 'hidden';
        
        // Mostrar información de la cita actual
        const currentDetails = document.querySelector('.current-details');
        if (currentDetails) {
            const fecha = new Date(citaActual.fecha);
            currentDetails.innerHTML = `
                <strong>Médico ID: ${citaActual.medico?.id || 'N/A'}</strong><br>
                📅 ${API.Formato.formatearFecha(fecha)} a las 🕐 ${citaActual.hora}<br>
                📍 ${citaActual.tipo}
            `;
        }
    }
}

function closeEditModal() {
    const modal = document.getElementById('editModal');
    if (modal) {
        modal.classList.remove('show');
        document.body.style.overflow = 'auto';
    }
}

async function saveChanges() {
    const fechaSeleccionada = document.querySelector('.day.selected');
    const horaSeleccionada = document.querySelector('.time-slot.selected');
    const tipoConsultaSelect = document.getElementById('tipo-consulta-modal');
    
    if (!fechaSeleccionada || !horaSeleccionada) {
        API.UI.mostrarError('Por favor, selecciona una fecha y hora');
        return;
    }
    
    try {
        const nuevoDia = parseInt(fechaSeleccionada.textContent);
        const nuevaFecha = new Date(2025, 10, nuevoDia); // Noviembre 2025
        const nuevaHora = horaSeleccionada.textContent + ':00';
        const tipoConsulta = tipoConsultaSelect ? tipoConsultaSelect.value : 'presencial';
        
        const datosActualizados = {
            fecha: API.Formato.fechaAISO(nuevaFecha),
            hora: nuevaHora,
            tipo: tipoConsulta === 'presencial' ? 'Consulta Presencial' : 'Consulta Virtual'
        };
        
        await API.Cita.actualizar(citaActual.id, datosActualizados);
        
        closeEditModal();
        
        // Mostrar mensaje de éxito
        const successMsg = document.getElementById('successMessage');
        if (successMsg) {
            successMsg.classList.add('show');
            window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
        }
        
        // Recargar citas
        const usuario = API.Session.obtenerUsuario();
        setTimeout(() => {
            cargarCitasPaciente(usuario.id);
            if (successMsg) {
                successMsg.classList.remove('show');
            }
        }, 3000);
        
    } catch (error) {
        console.error('Error al modificar cita:', error);
        API.UI.mostrarError('Error al modificar la cita');
    }
}

async function confirmarCancelacionDirecta(citaId) {
    if (!API.UI.confirmar('¿Estás seguro de que deseas cancelar esta cita?')) {
        return;
    }
    
    try {
        await API.Cita.cancelar(citaId);
        
        API.UI.mostrarExito('Cita cancelada exitosamente');
        
        // Recargar citas
        const usuario = API.Session.obtenerUsuario();
        cargarCitasPaciente(usuario.id);
        
    } catch (error) {
        console.error('Error al cancelar cita:', error);
        API.UI.mostrarError('Error al cancelar la cita');
    }
}

// ============================================
// FUNCIONES PARA CANCELAR-CITA.HTML
// ============================================

function inicializarCancelarCita() {
    API.Session.requiereAutenticacion();
    
    const usuario = API.Session.obtenerUsuario();
    
    // Actualizar información del usuario
    const userInfoElement = document.querySelector('.user-info');
    if (userInfoElement) {
        userInfoElement.innerHTML = `
            <span>👤 ${usuario.nombre}</span>
            <span>|</span>
            <span>📧 ${usuario.correo}</span>
        `;
    }

    cargarCitasParaCancelar(usuario.id);
}

async function cargarCitasParaCancelar(pacienteId) {
    try {
        const todasLasCitas = await API.Cita.obtenerPorPaciente(pacienteId);
        
        // Separar por estado (para esta demo, todas son "pendientes")
        const pendientes = todasLasCitas.filter(c => c.tipo !== 'CANCELADA');
        const canceladas = [];
        
        mostrarCitasPorTab(pendientes, canceladas);
        
    } catch (error) {
        console.error('Error al cargar citas:', error);
        API.UI.mostrarError('Error al cargar las citas');
    }
}

function mostrarCitasPorTab(pendientes, canceladas) {
    // Mostrar en tab de pendientes
    const pendingTab = document.getElementById('pending-tab');
    if (pendingTab) {
        const appointmentsList = pendingTab.querySelector('.appointments-list');
        if (appointmentsList) {
            appointmentsList.innerHTML = '';
            
            if (pendientes.length === 0) {
                appointmentsList.innerHTML = '<p style="text-align: center; color: #999; padding: 40px;">No tienes citas pendientes.</p>';
            } else {
                pendientes.forEach(cita => {
                    appointmentsList.appendChild(crearTarjetaCita(cita, false));
                });
            }
        }
    }
    
    // Mostrar en tab de canceladas
    const cancelledTab = document.getElementById('cancelled-tab');
    if (cancelledTab) {
        const appointmentsList = cancelledTab.querySelector('.appointments-list');
        if (appointmentsList) {
            appointmentsList.innerHTML = '';
            
            if (canceladas.length === 0) {
                appointmentsList.innerHTML = '<p style="text-align: center; color: #999; padding: 40px;">No tienes citas canceladas.</p>';
            } else {
                canceladas.forEach(cita => {
                    appointmentsList.appendChild(crearTarjetaCita(cita, true));
                });
            }
        }
    }
}

function crearTarjetaCita(cita, esCancelada) {
    const div = document.createElement('div');
    div.className = 'appointment-card' + (esCancelada ? ' cancelled' : '');
    
    const fecha = new Date(cita.fecha);
    const fechaFormateada = API.Formato.formatearFecha(fecha);
    
    div.innerHTML = `
        <div class="appointment-icon">👨‍⚕️</div>
        <div class="appointment-info">
            <div class="appointment-doctor">Médico ID: ${cita.medico?.id || 'N/A'}</div>
            <div class="appointment-specialty">${cita.tipo}</div>
            <div class="appointment-datetime">
                <span>📅 ${fechaFormateada}</span>
                <span>🕐 ${cita.hora}</span>
            </div>
            <span class="status-badge ${esCancelada ? 'status-cancelled' : 'status-pending'}">
                ${esCancelada ? '✗ Cancelada' : '⏳ Pendiente'}
            </span>
        </div>
        <div class="appointment-actions">
            ${!esCancelada ? `
                <button class="btn-action btn-details">👁️ Ver</button>
                <button class="btn-action btn-cancel-appointment" onclick="openCancelModal(${cita.id}, 'Médico', '${cita.tipo}', '${fechaFormateada}', '${cita.hora}')">🗑️ Cancelar</button>
            ` : `
                <button class="btn-action btn-details">👁️ Ver Detalles</button>
            `}
        </div>
    `;
    
    return div;
}

function openCancelModal(citaId, doctor, especialidad, fecha, hora) {
    const modal = document.getElementById('cancelModal');
    if (!modal) return;
    
    const details = document.getElementById('modalAppointmentDetails');
    
    // Guardar el ID de la cita a cancelar
    modal.dataset.citaId = citaId;
    
    if (details) {
        details.innerHTML = `
            <strong>${doctor}</strong> - ${especialidad}<br>
            📅 ${fecha}<br>
            🕐 ${hora}<br>
            📍 Consulta Presencial
        `;
    }
    
    modal.classList.add('show');
    document.body.style.overflow = 'hidden';
}

function closeCancelModal() {
    const modal = document.getElementById('cancelModal');
    if (modal) {
        modal.classList.remove('show');
        document.body.style.overflow = 'auto';
    }
}

async function confirmCancellation() {
    const modal = document.getElementById('cancelModal');
    if (!modal) return;
    
    const citaId = parseInt(modal.dataset.citaId);
    
    try {
        await API.Cita.cancelar(citaId);
        
        closeCancelModal();
        
        // Mostrar mensaje de éxito
        const successMsg = document.getElementById('successMessage');
        if (successMsg) {
            successMsg.classList.add('show');
            window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
        }
        
        // Recargar citas
        const usuario = API.Session.obtenerUsuario();
        setTimeout(() => {
            cargarCitasParaCancelar(usuario.id);
            if (successMsg) {
                successMsg.classList.remove('show');
            }
        }, 3000);
        
    } catch (error) {
        console.error('Error al cancelar cita:', error);
        API.UI.mostrarError('Error al cancelar la cita');
    }
}

function showTab(tabName) {
    // Ocultar todos los tabs
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.style.display = 'none';
    });
    
    // Remover clase active de todos los botones
    document.querySelectorAll('.tab').forEach(btn => {
        btn.classList.remove('active');
    });
    
    // Mostrar el tab seleccionado
    const selectedTab = document.getElementById(tabName + '-tab');
    if (selectedTab) {
        selectedTab.style.display = 'block';
    }
    
    // Agregar clase active al botón correspondiente
    if (event && event.target) {
        event.target.classList.add('active');
    }
}

// Auto-inicializar según la página
document.addEventListener('DOMContentLoaded', function() {
    const isModificarPage = window.location.pathname.includes('modificar-cita');
    const isCancelarPage = window.location.pathname.includes('cancelar-cita');
    
    if (isModificarPage) {
        inicializarModificarCita();
        
        // Configurar interactividad del calendario en modal
        document.querySelectorAll('.day:not(.disabled):not(.current)').forEach(day => {
            day.addEventListener('click', function() {
                document.querySelectorAll('.day').forEach(d => d.classList.remove('selected'));
                this.classList.add('selected');
            });
        });

        // Configurar interactividad de horarios en modal
        document.querySelectorAll('.time-slot:not(.disabled):not(.current)').forEach(slot => {
            slot.addEventListener('click', function() {
                document.querySelectorAll('.time-slot').forEach(s => s.classList.remove('selected'));
                this.classList.add('selected');
            });
        });
    } else if (isCancelarPage) {
        inicializarCancelarCita();
    }
});