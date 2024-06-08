import React, { useState, useEffect } from 'react';
import api from '../api';
import '../css/AvanceProyecto.css';

const AvanceProyecto = () => {
  const [smipNumber, setSmipNumber] = useState('');
  const [projectName, setProjectName] = useState('');
  const [rows, setRows] = useState([]);
  const [smipOptions, setSmipOptions] = useState([]);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);

  useEffect(() => {
    const fetchSmipOptions = async () => {
      try {
        const response = await api.get('/smip/listAllSmip');
        setSmipOptions(response.data.object || []);
      } catch (err) {
        setError('Error fetching SMIP options');
        console.error(err);
      }
    };
    fetchSmipOptions();
  }, []);

  const handleSmipChange = async (e) => {
    const smip = e.target.value;
    setSmipNumber(smip);
    try {
      // Fetch project name by smip number
      const projectResponse = await api.get(`/smip/projectBySmip/${smip}`);
      if (projectResponse.data.object) {
        setProjectName(projectResponse.data.object.nombreProyecto);
      } else {
        setProjectName('');
      }

      // Fetch rows by smip number
      const rowsResponse = await api.get(`/renglonInicial/listBySmip/${smip}`);
      setRows(rowsResponse.data.object || []);
      setError(null);
    } catch (err) {
      setProjectName('');
      setRows([]);
      setError('Error fetching project name or rows');
      console.error(err);
    }
  };

  const handleUpdateProgress = (index, field, value) => {
    const updatedRows = [...rows];
    updatedRows[index][field] = parseFloat(value);
    setRows(updatedRows);
  };

  const handleSaveProgress = async () => {
    try {
      // Aquí podrías enviar los datos actualizados a la API
      setSuccessMessage('Progreso guardado exitosamente');
      setError(null);
    } catch (err) {
      setError('Error guardando el progreso');
      setSuccessMessage(null);
      console.error(err);
    }
  };

  return (
    <div className="avance-proyecto">
      <div className="form-header">
        <h2>Avance del Proyecto</h2>
      </div>
      {error && <div className="error">{error}</div>}
      {successMessage && <div className="success">{successMessage}</div>}
      <form>
        <div className="form-group">
          <label>Seleccionar número de SMIP</label>
          <select value={smipNumber} onChange={handleSmipChange} required>
            <option value="">Seleccione un SMIP</option>
            {smipOptions.map((smip) => (
              <option key={smip.idSmip} value={smip.idSmip}>
                {smip.numeroSmip}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label>Nombre del proyecto</label>
          <p>{projectName}</p>
        </div>
      </form>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Número de Renglón de Trabajo</th>
              <th>Renglón de Trabajo</th>
              <th>Unidad de Medida</th>
              <th>Cantidad</th>
              <th>Progreso Actual</th>
              <th>Progreso Acumulado</th>
              <th>Progreso Pendiente</th>
            </tr>
          </thead>
          <tbody>
            {rows.map((row, index) => (
              <tr key={index}>
                <td>{row.numRenglonTrabajo}</td>
                <td>{row.renglonTrabajo}</td>
                <td>{row.unidadMedida}</td>
                <td>{parseFloat(row.cantidad).toFixed(2)}</td>
                <td>
                  <input
                    type="number"
                    step="0.01"
                    value={row.progresoActual || ''}
                    onChange={(e) => handleUpdateProgress(index, 'progresoActual', e.target.value)}
                  />
                </td>
                <td>{parseFloat(row.progresoAcumulado || 0).toFixed(2)}</td>
                <td>{(parseFloat(row.cantidad) - parseFloat(row.progresoAcumulado || 0)).toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <button onClick={handleSaveProgress}>Guardar Progreso</button>
    </div>
  );
};

export default AvanceProyecto;
