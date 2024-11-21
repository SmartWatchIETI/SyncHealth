import React, { useEffect, useState } from 'react';
import apiClient from './apiClient';
import 'bootstrap/dist/css/bootstrap.min.css';

function ListUsers() {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await apiClient.get('/users');
                setUsers(response.data);
            } catch (err) {
                setError('Error al cargar los usuarios.');
            }
        };
        fetchUsers();
    }, []);

    return (
        <div className="container mt-5">
            <h2 className="text-center">Lista de Usuarios</h2>
            {error && <div className="alert alert-danger">{error}</div>}
            <table className="table table-striped mt-4">
                <thead className="table-dark">
                    <tr>
                        <th>Nombre Completo</th>
                        <th>Correo Electrónico</th>
                        <th>Ciudad</th>
                    </tr>
                </thead>
                <tbody>
                    {users.length > 0 ? (
                        users.map((user) => (
                            <tr key={user.id}>
                                <td>{user.fullName}</td>
                                <td>{user.email}</td>
                                <td>{user.city}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="3" className="text-center">No hay usuarios disponibles</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default ListUsers;
