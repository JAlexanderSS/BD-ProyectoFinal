import React, { useState } from 'react';
import axios from 'axios';
import '../css/AsociarSMIP.css';

const AsociarSMIP = () => {
  const [noSnip, setNoSnip] = useState('');
  const [noSmip, setNoSmip] = useState('');
  const [montoProyecto, setMontoProyecto] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/smips', { noSnip, noSmip, montoProyecto });
      setNoSnip('');
      setNoSmip('');
      setMontoProyecto('');
      // fetchSnips(); // Implementar si es necesario
    } catch (error) {
      console.error("There was an error associating the SMIP!", error);
    }
  };

  return (
    <div className="asociar-smip">
      <div className="form-header">
        <h2>Asociar a SMIP</h2>
      </div>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Numero de Snip</label>
          <input
            type="number"
            value={noSnip}
            onChange={(e) => setNoSnip(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Numero de Smip</label>
          <input
            type="number"
            value={noSmip}
            onChange={(e) => setNoSmip(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Monto del Proyecto</label>
          <input
            type="number"
            value={montoProyecto}
            onChange={(e) => setMontoProyecto(e.target.value)}
            required
          />
        </div>
        <button type="submit">Asociar</button>
      </form>
    </div>
  );
};

export default AsociarSMIP;
