import React, { useState } from 'react';
import Menu from './components/Menu';
import RegistroNombre from './components/RegistroNombre';
import AsociarSMIP from './components/AsociarSMIP';
import RegistroRenglones from './components/RegistroRenglones';
import AvanceProyecto from './components/AvanceProyecto';
import './App.css';

function App() {
  const [activeComponent, setActiveComponent] = useState('nombre');

  const renderComponent = () => {
    switch (activeComponent) {
      case 'nombre':
        return <RegistroNombre />;
      case 'smip':
        return <AsociarSMIP />;
      case 'renglones':
        return <RegistroRenglones />;
      case 'avance':
        return <AvanceProyecto />;
      default:
        return <RegistroNombre />;
    }
  };

  return (
    <div className="App">
      <Menu setActiveComponent={setActiveComponent} />
      {renderComponent()}
    </div>
  );
}

export default App;
