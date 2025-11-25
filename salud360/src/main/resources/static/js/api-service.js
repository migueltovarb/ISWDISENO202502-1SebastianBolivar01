// ============================================
// api-service.js
// Servicio para conectar con el backend Spring Boot
// ============================================

const API_BASE_URL = 'http://localhost:8080/api';

const headers = {
    'Content-Type': 'application/json'
};

async function handleResponse(response) {
    if (!response.ok) {
        const error = await response.json().catch(() => ({}));
        throw new Error(error.message || `HTTP error! status: ${response.status}`);
    }
    return response.json();
}

// ============================================
// API de Pacientes
// ============================================

const PacienteAPI = {
    registrar: async (pacienteData) => {
        const response = await fetch(`${API_BASE_URL}/pacientes`, {
            method: 'POST',
            headers,
            body: JSON.stringify(pacienteData)
        });
        return handleResponse(response);
    },

    obtenerPorId: async (id) => {
        const response = await fetch(`${API_BASE_URL}/pacientes/${id}`);
        return handleResponse(response);
    },

    obtenerTodos: async () => {
        const response = await fetch(`${API_BASE_URL}/pacientes`);
        return handleResponse(response);
    },

    buscarPorCorreo: async (correo) => {
        const response = await fetch(`${API_BASE_URL}/pacientes/buscar?correo=${encodeURIComponent(correo)}`);
        return handleResponse(response);
    },

    obtenerHistorialCitas: async (id) => {
        const response = await fetch(`${API_BASE_URL}/pacientes/${id}/citas`);
        return handleResponse(response);
    },

    actualizar: async (id, pacienteData) => {
        const response = await fetch(`${API_BASE_URL}/pacientes/${id}`, {
            method: 'PUT',
            headers,
            body: JSON.stringify(pacienteData)
        });
        return handleResponse(response);
    }
};

// ============================================
// API de Médicos
// ============================================

const MedicoAPI = {
    obtenerTodos: async () => {
        const response = await fetch(`${API_BASE_URL}/medicos`);
        return handleResponse(response);
    },

    obtenerPorId: async (id) => {
        const response = await fetch(`${API_BASE_URL}/medicos/${id}`);
        return handleResponse(response);
    },

    buscarPorEspecialidad: async (especialidad) => {
        const response = await fetch(`${API_BASE_URL}/medicos/especialidad/${encodeURIComponent(especialidad)}`);
        return handleResponse(response);
    },

    obtenerAgenda: async (id) => {
        const response = await fetch(`${API_BASE_URL}/medicos/${id}/agenda`);
        return handleResponse(response);
    },

    registrar: async (medicoData) => {
        const response = await fetch(`${API_BASE_URL}/medicos`, {
            method: 'POST',
            headers,
            body: JSON.stringify(medicoData)
        });
        return handleResponse(response);
    }
};

// ============================================
// API de Citas
// ============================================

const CitaAPI = {
    crear: async (citaData) => {
        const response = await fetch(`${API_BASE_URL}/citas`, {
            method: 'POST',
            headers,
            body: JSON.stringify(citaData)
        });
        return handleResponse(response);
    },

    obtenerPorId: async (id) => {
        const response = await fetch(`${API_BASE_URL}/citas/${id}`);
        return handleResponse(response);
    },

    obtenerTodas: async () => {
        const response = await fetch(`${API_BASE_URL}/citas`);
        return handleResponse(response);
    },

    obtenerPorPaciente: async (pacienteId) => {
        const response = await fetch(`${API_BASE_URL}/citas/paciente/${pacienteId}`);
        return handleResponse(response);
    },

    obtenerPorMedico: async (medicoId) => {
        const response = await fetch(`${API_BASE_URL}/citas/medico/${medicoId}`);
        return handleResponse(response);
    },

    obtenerFuturasPaciente: async (pacienteId) => {
        const response = await fetch(`${API_BASE_URL}/citas/paciente/${pacienteId}/futuras`);
        return handleResponse(response);
    },

    obtenerFuturasMedico: async (medicoId) => {
        const response = await fetch(`${API_BASE_URL}/citas/medico/${medicoId}/futuras`);
        return handleResponse(response);
    },

    actualizar: async (id, citaData) => {
        const response = await fetch(`${API_BASE_URL}/citas/${id}`, {
            method: 'PUT',
            headers,
            body: JSON.stringify(citaData)
        });
        return handleResponse(response);
    },

    cancelar: async (id) => {
        const response = await fetch(`${API_BASE_URL}/citas/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            return { success: true, message: 'Cita cancelada exitosamente' };
        }
        throw new Error('Error al cancelar la cita');
    },

    obtenerPorTipo: async (tipo) => {
        const response = await fetch(`${API_BASE_URL}/citas/tipo/${encodeURIComponent(tipo)}`);
        return handleResponse(response);
    },

    obtenerPorFecha: async (fecha) => {
        const response = await fetch(`${API_BASE_URL}/citas/fecha?fecha=${fecha}`);
        return handleResponse(response);
    }
};

// ============================================
// Gestión de Sesión (LocalStorage)
// ============================================

const SessionManager = {
    guardarUsuario: (usuario) => {
        localStorage.setItem('usuario', JSON.stringify(usuario));
    },

    obtenerUsuario: () => {
        const usuario = localStorage.getItem('usuario');
        return usuario ? JSON.parse(usuario) : null;
    },

    cerrarSesion: () => {
        localStorage.removeItem('usuario');
        window.location.href = 'login.html';
    },

    hayUsuarioActivo: () => {
        return localStorage.getItem('usuario') !== null;
    },

    requiereAutenticacion: () => {
        if (!SessionManager.hayUsuarioActivo()) {
            window.location.href = 'login.html';
        }
    }
};

// ============================================
// Utilidades de Validación
// ============================================

const Validaciones = {
    esEmailValido: (email) => {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    },

    esContraseniaValida: (contrasenia) => {
        return contrasenia && contrasenia.length >= 6;
    },

    esHoraValida: (hora) => {
        const regex = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/;
        return regex.test(hora);
    },

    noEstaVacio: (valor) => {
        return valor && valor.trim().length > 0;
    }
};

// ============================================
// Utilidades de Formato
// ============================================

const Formato = {
    formatearFecha: (fecha) => {
        if (!fecha) return '';
        const date = new Date(fecha);
        const dia = String(date.getDate()).padStart(2, '0');
        const mes = String(date.getMonth() + 1).padStart(2, '0');
        const anio = date.getFullYear();
        return `${dia}/${mes}/${anio}`;
    },

    fechaAISO: (fecha) => {
        if (!fecha) return '';
        const date = new Date(fecha);
        return date.toISOString().split('T')[0];
    },

    formatearFechaParaAPI: (fechaString) => {
        const partes = fechaString.includes('/') 
            ? fechaString.split('/').reverse() 
            : fechaString.split('-');
        return `${partes[0]}-${partes[1]}-${partes[2]}`;
    }
};

// ============================================
// UI
// ============================================

const UI = {
    mostrarError: (mensaje) => {
        alert('❌ Error: ' + mensaje);
    },

    mostrarExito: (mensaje) => {
        alert('✅ ' + mensaje);
    },

    confirmar: (mensaje) => {
        return confirm(mensaje);
    },

    mostrarCargando: (elemento) => {
        if (elemento) {
            elemento.disabled = true;
            elemento.innerHTML = '⏳ Cargando...';
        }
    },

    ocultarCargando: (elemento, textoOriginal) => {
        if (elemento) {
            elemento.disabled = false;
            elemento.innerHTML = textoOriginal;
        }
    }
};

window.API = {
    Paciente: PacienteAPI,
    Medico: MedicoAPI,
    Cita: CitaAPI,
    Session: SessionManager,
    Validaciones,
    Formato,
    UI
};