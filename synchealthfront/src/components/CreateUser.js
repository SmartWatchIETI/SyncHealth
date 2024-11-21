import React, { useState } from 'react';
import apiClient from './apiClient';
import 'bootstrap/dist/css/bootstrap.min.css';

function CreateUser() {
    const [formData, setFormData] = useState({
        fullName: '',
        email: '',
        password: '',
        bornDate: '',
        city: '',
    });

    const [success, setSuccess] = useState(false);
    const [error, setError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSuccess(false);
        setError('');
        try {
            await apiClient.post('/users/create', formData);
            setSuccess(true);
        } catch (err) {
            setError('Error al crear el usuario. Verifica los datos.');
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center">Crear Usuario</h2>
            {success && <div className="alert alert-success">Usuario creado con éxito.</div>}
            {error && <div className="alert alert-danger">{error}</div>}
            <form onSubmit={handleSubmit} className="col-md-6 mx-auto border p-4 rounded shadow-sm">
                <div className="mb-3">
                    <label htmlFor="fullName" className="form-label">Nombre Completo</label>
                    <input
                        type="text"
                        name="fullName"
                        id="fullName"
                        className="form-control"
                        value={formData.fullName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Correo Electrónico</label>
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
                <div className="mb-3">
                    <label htmlFor="bornDate" className="form-label">Fecha de Nacimiento</label>
                    <input
                        type="date"
                        name="bornDate"
                        id="bornDate"
                        className="form-control"
                        value={formData.bornDate}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="city" className="form-label">Ciudad</label>
                    <input
                        type="text"
                        name="city"
                        id="city"
                        className="form-control"
                        value={formData.city}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary w-100">Crear Usuario</button>
            </form>
        </div>
    );
}

export default CreateUser;
