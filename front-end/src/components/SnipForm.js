import React, { useState } from 'react';
import axios from 'axios';

const SnipForm = ({ fetchSnips }) => {
  const [noSnip, setNoSnip] = useState('');
  const [nombreProyecto, setNombreProyecto] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Enviar una solicitud POST al API
      await axios.post('http://localhost:8080/api/snips', { noSnip, nombreProyecto });
      // Limpiar los campos del formulario después de enviar
      setNoSnip('');
      setNombreProyecto('');
      // Volver a cargar la lista de Snips
      fetchSnips();
    } catch (error) {
      console.error("There was an error creating the snip!", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>No Snip:</label>
        <input
          type="number"
          value={noSnip}
          onChange={(e) => setNoSnip(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Nombre Proyecto:</label>
        <input
          type="text"
          value={nombreProyecto}
          onChange={(e) => setNombreProyecto(e.target.value)}
          required
        />
      </div>
      <button type="submit">Add Snip</button>
    </form>
  );
};

export default SnipForm;