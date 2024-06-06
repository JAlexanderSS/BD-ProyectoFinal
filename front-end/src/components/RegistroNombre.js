import React, { useState } from 'react';
import axios from 'axios';
import '../css/RegistroNombre.css';

const RegistroNombre = () => {
  const [noSnip, setNoSnip] = useState('');
  const [nombreProyecto, setNombreProyecto] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/snips', { noSnip, nombreProyecto });
      setNoSnip('');
      setNombreProyecto('');
      // fetchSnips(); // Implementar si es necesario
    } catch (error) {
      console.error("There was an error creating the snip!", error);
    }
  };

  return (
    <div className="registro-nombre">
      <div className="form-header">
        <h2>Creación del nombre para proyecto</h2>
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
          <label>Nombre del Proyecto</label>
          <input
            type="text"
            value={nombreProyecto}
            onChange={(e) => setNombreProyecto(e.target.value)}
            required
          />
        </div>
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default RegistroNombre;