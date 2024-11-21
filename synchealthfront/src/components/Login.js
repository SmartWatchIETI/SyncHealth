import React, { useState } from 'react';
import apiClient from './apiClient'; // Archivo con Axios configurado
import 'bootstrap/dist/css/bootstrap.min.css';

function Login() {
    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });

    const [error, setError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
            const response = await apiClient.post('/authenticate', {
                email: formData.email,
                hashPassword: formData.password,
            });
            alert('Inicio de sesión exitoso');
            localStorage.setItem('token', response.data.token); // Guardar el token JWT en el almacenamiento local
        } catch (error) {
            setError('Usuario o contraseña incorrectos');
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center">Iniciar Sesión</h2>
            <form onSubmit={handleSubmit} className="col-md-6 mx-auto border p-4 rounded shadow-sm">
                {error && <div className="alert alert-danger">{error}</div>}
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Correo electrónico</label>
                    <input
                        type="email"
                        name="email"
                        id="email"
                        className="form-control"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Contraseña</label>
                    <input
                        type="password"
                        name="password"
                        id="password"
                        className="form-control"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary w-100">Iniciar Sesión</button>
            </form>
        </div>
    );
}

export default Login;
