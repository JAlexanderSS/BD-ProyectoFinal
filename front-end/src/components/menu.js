import React, { useState } from 'react';
import '../css/Menu.css';

const Menu = ({ setActiveComponent }) => {
  const [isSubMenuVisible, setIsSubMenuVisible] = useState(false);

  return (
    <div className="menu">
      <h2 
        className="menu-header"
        onMouseEnter={() => setIsSubMenuVisible(true)}
        onMouseLeave={() => setIsSubMenuVisible(false)}
      >
        Registro
      </h2>
      {isSubMenuVisible && (
        <div 
          className="submenu"
          onMouseEnter={() => setIsSubMenuVisible(true)}
          onMouseLeave={() => setIsSubMenuVisible(false)}
        >
          <button onClick={() => setActiveComponent('nombre')}>Registrar nombre</button>
          <button onClick={() => setActiveComponent('smip')}>Asociar SMIP</button>
          <button onClick={() => setActiveComponent('renglones')}>Registro de renglones</button>
        </div>
      )}
    </div>
  );
};

export default Menu;
