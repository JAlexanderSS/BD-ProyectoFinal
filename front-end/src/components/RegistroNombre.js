import React, { useState } from 'react';
import api from '../api';
import '../css/RegistroNombre.css';

const RegistroNombre = () => {
  const [noSnip, setNoSnip] = useState('');
  const [nombreProyecto, setNombreProyecto] = useState('');
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/snip/saveSnip', { noSnip, nombreProyecto });
      setSuccessMessage('Proyecto guardado correctamente');
      setError(null);
      setNoSnip('');
      setNombreProyecto('');
    } catch (err) {
      setError(err.response ? err.response.data.mensaje : 'Error al guardar el proyecto');
      setSuccessMessage(null);
      console.error(err);
    }
  };

  return (
    <div className="registro-nombre">
      <div className="form-header">
        <h2>Creación del nombre para proyecto</h2>
      </div>
      {error && <div className="error">{error}</div>}
      {successMessage && <div className="success">{successMessage}</div>}
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
