import React from 'react';
import '../css/menu.css';

const Menu = ({ setActiveComponent }) => {
  return (
    <div className="menu">
      <div className="menu-item">
        <span>Registro</span>
        <div className="submenu">
          <button onClick={() => setActiveComponent('nombre')}>Registrar nombre</button>
          <button onClick={() => setActiveComponent('smip')}>Asociar SMIP</button>
          <button onClick={() => setActiveComponent('renglones')}>Registro de renglones</button>
        </div>
      </div>
    </div>
  );
};

export default Menu;