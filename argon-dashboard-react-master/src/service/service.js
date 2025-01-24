export async function allC() {
    try {
        const response = await fetch(`http://localhost:9000/mostrar/divisiones`);
        return await response.json();
    } catch (error) {
        throw error;
    }
}

export async function filtrarDivisiones(id) {  
    try {
        const response = await fetch(`http://localhost:9000/divisiones/${id}`);
        return await response.json();
    } catch (error) {
        throw error;
    }
}

export async function orndearNombre(id) {  
    try {
        const response = await fetch(`http://localhost:9000/equipos/ordenar/nombre/${id}`);
        return await response.json();
    } catch (error) {
        throw error;
    }
}

export async function orndearFecha(id) {  
    try {
        const response = await fetch(`http://localhost:9000/equipos/ordenar/fecha/${id}`);
        return await response.json();
    } catch (error) {
        throw error;
    }
}

export async function orndearGoles(id) {  
    try {
        const response = await fetch(`http://localhost:9000/equipos/ordenar/goles/${id}`);
        return await response.json();
    } catch (error) {
        throw error;
    }
}

export async function orndearTitulos(id) {  
    try {
        const response = await fetch(`http://localhost:9000/equipos/ordenar/titulos/${id}`);
        return await response.json();
    } catch (error) {
        throw error;
    }
}



export async function filtrarEquipos(id, filtradoEquiposDto) {
    try {
      const response = await fetch(`http://localhost:9000/equipos/filtrar/${id}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(filtradoEquiposDto),
      });
  
      if (!response.ok) {
        throw new Error(`Error en la solicitud: ${response.status} - ${response.statusText}`);
      }
  
      const data = await response.json();
     
  
      return data;
    } catch (error) {
      console.error('Error al filtrar equipos:', error.message);
      throw error;
    }
  }
  
  
  
  
  