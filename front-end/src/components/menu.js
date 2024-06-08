import React, { useState } from 'react';
import '../css/Menu.css';

const Menu = ({ setActiveComponent }) => {
  const [isRegistroSubMenuVisible, setIsRegistroSubMenuVisible] = useState(false);
  const [isAvanceSubMenuVisible, setIsAvanceSubMenuVisible] = useState(false);

  return (
    <div className="menu">
      <div
        className="menu-header"
        onMouseEnter={() => setIsRegistroSubMenuVisible(true)}
        onMouseLeave={() => setIsRegistroSubMenuVisible(false)}
      >
        <h2>Registro</h2>
        {isRegistroSubMenuVisible && (
          <div className="submenu">
            <button onClick={() => setActiveComponent('nombre')}>Registrar nombre</button>
            <button onClick={() => setActiveComponent('smip')}>Asociar SMIP</button>
            <button onClick={() => setActiveComponent('renglones')}>Registro de renglones</button>
          </div>
        )}
      </div>
      <div
        className="menu-header"
        onMouseEnter={() => setIsAvanceSubMenuVisible(true)}
        onMouseLeave={() => setIsAvanceSubMenuVisible(false)}
      >
        <h2>Avance</h2>
        {isAvanceSubMenuVisible && (
          <div className="submenu">
            <button onClick={() => setActiveComponent('avance')}>Avance del Proyecto</button>
          </div>
        )}
      </div>
    </div>
  );
};

export default Menu;
