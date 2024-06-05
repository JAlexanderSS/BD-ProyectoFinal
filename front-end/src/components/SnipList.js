import React from 'react';
import axios from 'axios';

const SnipList = ({ snips, fetchSnips }) => {

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/snips/${id}`);
      fetchSnips();
    } catch (error) {
      console.error("There was an error deleting the snip!", error);
    }
  };

  return (
    <div>
      <h2>Snip List</h2>
      <ul>
        {snips.map((snip) => (
          <li key={snip.idSnip}>
            {snip.noSnip} - {snip.nombreProyecto}
            <button onClick={() => handleDelete(snip.idSnip)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SnipList;
