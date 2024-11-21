import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import CreateUser from './components/CreateUser';
import ListUsers from './components/ListUsers';
import Home from './components/Home';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/create-user" element={<CreateUser />} />
                <Route path="/list-users" element={<ListUsers />} />
            </Routes>
        </Router>
    );
}

export default App;
