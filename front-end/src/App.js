import React, { useState } from 'react';
import './App.css';
import Menu from './components/menu';

function App() {
  const [activeComponent, setActiveComponent] = useState('');

  const renderComponent = () => {
    switch (activeComponent) {
      case 'nombre':
        return <div>Registro de Nombre</div>;
      case 'smip':
        return <div>Asociar SMIP</div>;
      case 'renglones':
        return <div>Registro de Renglones</div>;
      default:
        return <div>Seleccione una opción del menú</div>;
    }
  };

  return (
    <div className="App">
      <Menu setActiveComponent={setActiveComponent} />
      <div className="content">
        {renderComponent()}
      </div>
    </div>
  );
}

export default App;