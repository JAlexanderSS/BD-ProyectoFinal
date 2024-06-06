import React, { useState } from 'react';
import './App.css'; // Asegúrate de que App.css está en el mismo nivel que App.js
import Menu from './components/Menu';
import RegistroNombre from './components/RegistroNombre';

function App() {
  const [activeComponent, setActiveComponent] = useState('');

  const renderComponent = () => {
    switch (activeComponent) {
      case 'nombre':
        return <RegistroNombre />;
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
