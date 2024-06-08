import React, { useState, useEffect } from 'react';
import api from '../api';
import '../css/RegistroRenglones.css';

const RegistroRenglones = () => {
  const [smipNumber, setSmipNumber] = useState('');
  const [projectName, setProjectName] = useState('');
  const [workItemNumber, setWorkItemNumber] = useState('');
  const [workItem, setWorkItem] = useState('');
  const [unit, setUnit] = useState('');
  const [quantity, setQuantity] = useState('');
  const [unitCost, setUnitCost] = useState('');
  const [rows, setRows] = useState([]);
  const [smipOptions, setSmipOptions] = useState([]);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);
  const [editIndex, setEditIndex] = useState(-1);

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

  const handleAddRow = async (e) => {
    e.preventDefault();
    const newRow = {
      idSmip: smipNumber,
      numRenglonTrabajo: workItemNumber,
      renglonTrabajo: workItem,
      unidadMedida: unit,
      cantidad: parseFloat(quantity),
      costoUnitario: parseFloat(unitCost),
      costoTotal: (parseFloat(quantity) * parseFloat(unitCost)).toFixed(2),
    };

    try {
      if (editIndex === -1) {
        await api.post('/renglonInicial/saveRenglonInicial', newRow); // Enviar los datos del nuevo renglón a la API
        setRows([...rows, newRow].sort((a, b) => a.numRenglonTrabajo - b.numRenglonTrabajo));
      } else {
        const updatedRows = [...rows];
        updatedRows[editIndex] = newRow;
        setRows(updatedRows.sort((a, b) => a.numRenglonTrabajo - b.numRenglonTrabajo));
        setEditIndex(-1);
      }

      setWorkItemNumber('');
      setWorkItem('');
      setUnit('');
      setQuantity('');
      setUnitCost('');
      setSuccessMessage('Renglón agregado exitosamente');
      setError(null);
    } catch (err) {
      setError('Error adding row');
      setSuccessMessage(null);
      console.error(err);
    }
  };

  const handleEditRow = (index) => {
    const row = rows[index];
    setSmipNumber(row.idSmip);
    setWorkItemNumber(row.numRenglonTrabajo);
    setWorkItem(row.renglonTrabajo);
    setUnit(row.unidadMedida);
    setQuantity(row.cantidad);
    setUnitCost(row.costoUnitario);
    setEditIndex(index);
  };

  const handleDeleteRow = async (index) => {
    const row = rows[index];
    try {
      await api.delete(`/renglonInicial/deleteRenglonInicial/${row.numRenglonTrabajo}`);
      const updatedRows = rows.filter((_, i) => i !== index);
      setRows(updatedRows);
      setSuccessMessage('Renglón eliminado exitosamente');
      setError(null);
    } catch (err) {
      setError('Error deleting row');
      setSuccessMessage(null);
      console.error(err);
    }
  };

  const formatCurrency = (value) => {
    return new Intl.NumberFormat('es-GT', { style: 'currency', currency: 'GTQ' }).format(value);
  };

  return (
    <div className="registro-renglones">
      <div className="form-header">
        <h2>Registro de Renglones</h2>
      </div>
      {error && <div className="error">{error}</div>}
      {successMessage && <div className="success">{successMessage}</div>}
      <form onSubmit={handleAddRow}>
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
        <div className="form-group">
          <label>Número de Renglón de Trabajo</label>
          <input
            type="number"
            value={workItemNumber}
            onChange={(e) => setWorkItemNumber(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Renglón de trabajo</label>
          <input
            type="text"
            value={workItem}
            onChange={(e) => setWorkItem(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Unidad de Medida</label>
          <input
            type="text"
            value={unit}
            onChange={(e) => setUnit(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Cantidad</label>
          <input
            type="number"
            step="0.01"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Costo Unitario</label>
          <input
            type="number"
            step="0.01"
            value={unitCost}
            onChange={(e) => setUnitCost(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Total</label>
          <input
            type="text"
            value={quantity && unitCost ? formatCurrency(parseFloat(quantity) * parseFloat(unitCost)) : formatCurrency(0)}
            readOnly
          />
        </div>
        <button type="submit">{editIndex === -1 ? 'Agregar' : 'Actualizar'}</button>
      </form>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Número de SMIP</th>
              <th>Número de Renglón de Trabajo</th>
              <th>Renglón de Trabajo</th>
              <th>Unidad de Medida</th>
              <th>Cantidad</th>
              <th>Costo Unitario</th>
              <th>Total</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {rows.sort((a, b) => a.numRenglonTrabajo - b.numRenglonTrabajo).map((row, index) => (
              <tr key={index}>
                <td>{row.idSmip}</td>
                <td>{row.numRenglonTrabajo}</td>
                <td>{row.renglonTrabajo}</td>
                <td>{row.unidadMedida}</td>
                <td>{parseFloat(row.cantidad).toFixed(2)}</td>
                <td>{formatCurrency(row.costoUnitario)}</td>
                <td>{formatCurrency(row.costoTotal)}</td>
                <td>
                  <button onClick={() => handleEditRow(index)}>Editar</button>
                  <button onClick={() => handleDeleteRow(index)} className="delete-button">Eliminar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="total">
        <strong>Total del proyecto: {formatCurrency(rows.reduce((acc, row) => acc + parseFloat(row.costoTotal), 0))}</strong>
      </div>
    </div>
  );
};

export default RegistroRenglones;
