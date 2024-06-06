import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../css/RegistroRenglones.css';

const RegistroRenglones = () => {
  const [smipNumber, setSmipNumber] = useState('');
  const [projectName, setProjectName] = useState('');
  const [workItem, setWorkItem] = useState('');
  const [unit, setUnit] = useState('');
  const [quantity, setQuantity] = useState('');
  const [unitCost, setUnitCost] = useState('');
  const [rows, setRows] = useState([]);
  const [smipOptions, setSmipOptions] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch SMIP options from the API
    const fetchSmipOptions = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/smips'); // Change this to your API endpoint
        setSmipOptions(response.data);
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
      const response = await axios.get(`http://localhost:8080/api/project/${smip}`); // Change this to your API endpoint
      setProjectName(response.data.projectName);
    } catch (err) {
      setError('Error fetching project name');
      console.error(err);
    }
  };

  const handleAddRow = (e) => {
    e.preventDefault();
    const newRow = {
      smipNumber,
      projectName,
      workItem,
      unit,
      quantity: parseFloat(quantity).toFixed(2),
      unitCost: parseFloat(unitCost).toFixed(2),
      total: (parseFloat(quantity) * parseFloat(unitCost)).toFixed(2),
    };
    setRows([...rows, newRow]);
    setWorkItem('');
    setUnit('');
    setQuantity('');
    setUnitCost('');
  };

  return (
    <div className="registro-renglones">
      <div className="form-header">
        <h2>Registro de Renglones</h2>
      </div>
      {error && <div className="error">{error}</div>}
      <form onSubmit={handleAddRow}>
        <div className="form-group">
          <label>Seleccionar numero de SMIP</label>
          <select value={smipNumber} onChange={handleSmipChange} required>
            <option value="">Seleccione un SMIP</option>
            {smipOptions.map((smip) => (
              <option key={smip.id} value={smip.number}>
                {smip.number}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label>Nombre del proyecto</label>
          <p>{projectName}</p>
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
            type="number"
            value={quantity && unitCost ? (parseFloat(quantity) * parseFloat(unitCost)).toFixed(2) : 0}
            readOnly
          />
        </div>
        <button type="submit">Agregar</button>
      </form>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Numero de SMIP</th>
              <th>Nombre del Proyecto</th>
              <th>Renglón de trabajo</th>
              <th>Unidad de Medida</th>
              <th>Cantidad</th>
              <th>Costo Unitario</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>
            {rows.map((row, index) => (
              <tr key={index}>
                <td>{row.smipNumber}</td>
                <td>{row.projectName}</td>
                <td>{row.workItem}</td>
                <td>{row.unit}</td>
                <td>{parseFloat(row.quantity).toFixed(2)}</td>
                <td>{parseFloat(row.unitCost).toFixed(2)}</td>
                <td>{parseFloat(row.total).toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="total">
        <strong>Total del proyecto: {rows.reduce((acc, row) => acc + parseFloat(row.total), 0).toFixed(2)}</strong>
      </div>
    </div>
  );
};

export default RegistroRenglones;
