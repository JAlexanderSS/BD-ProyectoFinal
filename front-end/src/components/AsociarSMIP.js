import React, { useState, useEffect } from 'react';
import api from '../api';
import '../css/AsociarSMIP.css';

const AsociarSMIP = () => {
  const [smipNumber, setSmipNumber] = useState('');
  const [noSnip, setNoSnip] = useState('');
  const [montoProyecto, setMontoProyecto] = useState('');
  const [snipOptions, setSnipOptions] = useState([]);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);

  useEffect(() => {
    const fetchSnips = async () => {
      try {
        const response = await api.get('/snip/listAllSnip');
        setSnipOptions(response.data.object);
      } catch (err) {
        setError('Error fetching SNIPs');
        console.error(err);
      }
    };
    fetchSnips();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/smip/saveSmip', {
        numeroSmip: smipNumber,
        idSnip: noSnip,
        monto: montoProyecto
      });
      setSuccessMessage('SMIP asociado correctamente');
      setError(null);
      setNoSnip('');
      setSmipNumber('');
      setMontoProyecto('');
    } catch (err) {
      setError(err.response && err.response.data && err.response.data.mensaje ? err.response.data.mensaje : 'Error al asociar el SMIP');
      setSuccessMessage(null);
      console.error(err);
    }
  };

  return (
    <div className="asociar-smip">
      <div className="form-header">
        <h2>Asociar a SMIP</h2>
      </div>
      {error && <div className="error">{error}</div>}
      {successMessage && <div className="success">{successMessage}</div>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Seleccionar número de SNIP</label>
          <select value={noSnip} onChange={(e) => setNoSnip(e.target.value)} required>
            <option value="">Seleccione un SNIP</option>
            {snipOptions.map((snip) => (
              <option key={snip.idSnip} value={snip.idSnip}>
                {snip.noSnip} - {snip.nombreProyecto}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label>Número de SMIP</label>
          <input
            type="number"
            value={smipNumber}
            onChange={(e) => setSmipNumber(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Monto del Proyecto</label>
          <input
            type="number"
            step="0.01"
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
