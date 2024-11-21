import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function Home() {
    return (
        <div className="d-flex">
            {/* Sidebar */}
            <nav className="bg-light border-end" style={{ width: '80px', height: '100vh' }}>
                <ul className="nav flex-column text-center pt-4">
                    <li className="nav-item mb-4">
                        <i className="bi bi-person-circle fs-4"></i>
                    </li>
                    <li className="nav-item mb-4">
                        <i className="bi bi-calendar-check fs-4"></i>
                    </li>
                    <li className="nav-item mb-4">
                        <i className="bi bi-chat-dots fs-4"></i>
                    </li>
                    <li className="nav-item mb-4">
                        <i className="bi bi-gear fs-4"></i>
                    </li>
                </ul>
            </nav>

            {/* Main Content */}
            <div className="flex-grow-1 p-4">
                {/* Header */}
                <div className="d-flex justify-content-between align-items-center mb-4">
                    <div>
                        <h4>Your Health Today</h4>
                        <p className="text-muted">August 12, 2021</p>
                    </div>
                </div>

                {/* Health Info Cards */}
                <div className="row">
                    <div className="col-md-4 mb-4">
                        <div className="card border-light shadow-sm">
                            <div className="card-body">
                                <h5 className="card-title">Blood Sugar</h5>
                                <h2 className="text-primary">80 <small>mg/dL</small></h2>
                                <p className="text-success">Normal</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4 mb-4">
                        <div className="card border-light shadow-sm">
                            <div className="card-body">
                                <h5 className="card-title">Blood Pressure</h5>
                                <h2 className="text-primary">102 / 72 <small>mmHg</small></h2>
                                <p className="text-success">Normal</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4 mb-4">
                        <div className="card border-light shadow-sm">
                            <div className="card-body">
                                <h5 className="card-title">Heart Rate</h5>
                                <h2 className="text-primary">98 <small>bpm</small></h2>
                                <p className="text-success">Normal</p>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Bottom Section */}
                <div className="row">
                    {/* BMI Calculator */}
                    <div className="col-md-6 mb-4">
                        <div className="card border-light shadow-sm">
                            <div className="card-body">
                                <h5 className="card-title">BMI Calculator</h5>
                                <div className="d-flex justify-content-between">
                                    <div>
                                        <p>Height: <strong>170 cm</strong></p>
                                        <p>Weight: <strong>72 kg</strong></p>
                                    </div>
                                    <div>
                                        <p>BMI: <strong>24.9</strong></p>
                                        <p className="text-success">You're Healthy</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Body Measurements */}
                    <div className="col-md-6 mb-4">
                        <div className="card border-light shadow-sm">
                            <div className="card-body">
                                <h5 className="card-title">Body Measurements</h5>
                                <p className="text-muted">Last checked 2 days ago</p>
                                <div className="d-flex justify-content-between">
                                    <p>Chest: <strong>44.5 in</strong></p>
                                    <p>Waist: <strong>34 in</strong></p>
                                    <p>Hip: <strong>42.5 in</strong></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Home;
