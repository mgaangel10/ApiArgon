import React, { useState, useEffect } from "react";
import { Card, CardHeader, Table, Container, Row, Button, Badge, Input, Form, FormGroup, Label, Pagination, PaginationItem, PaginationLink } from "reactstrap";
import Header from "components/Headers/Header.js";
import { allC, filtrarDivisiones, filtrarEquipos, orndearNombre, orndearFecha, orndearGoles, orndearTitulos } from "../../service/service.js";

const TableServer = () => {
  const [divisiones, setDivisiones] = useState([]);
  const [equipos, setEquipos] = useState([]);
  const [divisionSeleccionada, setDivisionSeleccionada] = useState(null);
  const [filtroEquipos, setFiltroEquipos] = useState({
    numeroGoles: 0, 
    numeroTitulos: 0,
  });
  //paginacion
  const [currentPage, setCurrentPage] = useState(1); 
  const itemsPerPage = 4; 
  //mostrar todas divisiones
  useEffect(() => {
    const fetchDivisiones = async () => {
      try {
        const data = await allC();
        setDivisiones(data);
      } catch (error) {
        console.error("Error al obtener las divisiones:", error);
      }
    };
    fetchDivisiones();
  }, []);

  //filtrar equipos por division

  const handleDivisionClick = async (id, nombre) => {
    setDivisionSeleccionada(id); 
    try {
      const equiposFiltrados = await filtrarDivisiones(id);
      setEquipos(equiposFiltrados);
    } catch (error) {
      console.error("Error al filtrar los equipos:", error);
    }
  };
  // algoritmo para ordenar
  const handleOrdenar = async (criterio) => {
    try {
      let equiposOrdenados = [];
      switch (criterio) {
        case "nombre":
          equiposOrdenados = await orndearNombre(divisionSeleccionada);
          break;
        case "fecha":
          equiposOrdenados = await orndearFecha(divisionSeleccionada);
          break;
        case "goles":
          equiposOrdenados = await orndearGoles(divisionSeleccionada);
          break;
        case "titulos":
          equiposOrdenados = await orndearTitulos(divisionSeleccionada);
          break;
        default:
          break;
      }
      if (Array.isArray(equiposOrdenados)) {
        setEquipos(equiposOrdenados);
      } else {
        console.error("La respuesta de la API no es un array:", equiposOrdenados);
        setEquipos([]); 
      }
    } catch (error) {
      console.error("Error al ordenar los equipos:", error);
      setEquipos([]); 
    }
  };
  // filtrar por goles y titulos
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    
    setFiltroEquipos({
      ...filtroEquipos,
      [name]: value,
    });
  };

  const handleFiltrarEquipos = async () => {
    if (divisionSeleccionada === null) {
      alert("Primero selecciona una división.");
      return;
    }

    try {
      const equiposFiltrados = await filtrarEquipos(divisionSeleccionada, filtroEquipos);
      setEquipos(equiposFiltrados? equiposFiltrados : []);
    } catch (error) {
      console.error("Error al filtrar equipos:", error);
    }
  };

  // metodo de paginacion
  const paginate = (equipos, currentPage, itemsPerPage) => {
    const indexOfLastEquipo = currentPage * itemsPerPage;
    const indexOfFirstEquipo = indexOfLastEquipo - itemsPerPage;
    return equipos.slice(indexOfFirstEquipo, indexOfLastEquipo);
  };

  // total paginas
  const totalPages = Math.ceil(equipos.length / itemsPerPage);

  return (
    <>
      <Header />
      <Container className="mt--7" fluid>
        <Row>
          <div className="col">
            <Card className="shadow">
              <CardHeader className="border-0">
                {divisiones.map((division) => (
                  <Button
                    key={division.id}
                    color="primary"
                    className="mr-2"
                    onClick={() => handleDivisionClick(division.id, division.nombre)}
                    style={{
                      border: divisionSeleccionada === division.id ? "3px solid #007bff" : "none",
                    }}
                  >
                    {division.nombre}
                  </Button>
                ))}
              </CardHeader>

              {divisionSeleccionada && (
                <>
                  
                  <Form inline className="mb-3 mt-3">
                    <FormGroup className="mr-2">
                      <Label for="numeroGoles" className="mr-2">
                        Goles Totales:
                      </Label>
                      <Input
                        type="number"
                        name="numeroGoles"
                        id="numeroGoles"
                        value={filtroEquipos.numeroGoles}
                        onChange={handleInputChange}
                      />
                    </FormGroup>
                    <FormGroup className="mr-2">
                      <Label for="numeroTitulos" className="mr-2">
                        Títulos Obtenidos:
                      </Label>
                      <Input
                        type="number"
                        name="numeroTitulos"
                        id="numeroTitulos"
                        value={filtroEquipos.numeroTitulos}
                        onChange={handleInputChange}
                      />
                    </FormGroup>
                    <Button color="primary" onClick={handleFiltrarEquipos}>
                      Filtrar
                    </Button>
                  </Form>
                </>
              )}

              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">Imagen</th>
                    <th scope="col">
                      Nombre
                      <Button 
                        color="link" 
                        onClick={() => handleOrdenar("nombre")}
                        style={{ marginLeft: '8px' }} >
                        Ordenar
                      </Button>
                    </th>
                    <th scope="col">
                      Ciudad
                    </th>
                    <th scope="col">
                      Estadio
                    </th>
                    <th scope="col">
                      Fundado
                      <Button 
                        color="link" 
                        onClick={() => handleOrdenar("fecha")}
                        style={{ marginLeft: '8px' }} >
                        Ordenar
                      </Button>
                    </th>
                    <th scope="col">
                      Goles
                      <Button 
                        color="link" 
                        onClick={() => handleOrdenar("goles")}
                        style={{ marginLeft: '8px' }} >
                        Ordenar
                      </Button>
                    </th>
                    <th scope="col">
                      Títulos
                      <Button 
                        color="link" 
                        onClick={() => handleOrdenar("titulos")}
                        style={{ marginLeft: '8px' }} >
                        Ordenar
                      </Button>
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {Array.isArray(equipos) && equipos.length > 0 ? (
                    paginate(equipos, currentPage, itemsPerPage).map((equipo) => (
                      <tr key={equipo.idEquipo}>
                        <td><img src={equipo.imagen} alt={equipo.nombre} width="50" /></td>
                        <td>{equipo.nombre}</td>
                        <td>{equipo.ciudad}</td>
                        <td>{equipo.nombreEstadio}</td>
                        <td>{equipo.fundaoo}</td>
                        <td>{equipo.golesTotales}</td>
                        <td>{equipo.titulosObtenidos}</td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="7">No hay equipos disponibles.</td>
                    </tr>
                  )}
                </tbody>
              </Table>

              
              <Pagination className="pagination justify-content-end mb-0" listClassName="justify-content-end mb-0">
                <PaginationItem className={currentPage === 1 ? "disabled" : ""}>
                  <PaginationLink
                    href="#pablo"
                    onClick={(e) => {
                      e.preventDefault();
                      setCurrentPage(currentPage - 1);
                    }}
                    tabIndex="-1"
                  >
                    <i className="fas fa-angle-left" />
                    <span className="sr-only">Previous</span>
                  </PaginationLink>
                </PaginationItem>
                {[...Array(totalPages)].map((_, index) => (
                  <PaginationItem key={index} className={currentPage === index + 1 ? "active" : ""}>
                    <PaginationLink
                      href="#pablo"
                      onClick={(e) => {
                        e.preventDefault();
                        setCurrentPage(index + 1);
                      }}
                    >
                      {index + 1}
                    </PaginationLink>
                  </PaginationItem>
                ))}
                <PaginationItem className={currentPage === totalPages ? "disabled" : ""}>
                  <PaginationLink
                    href="#pablo"
                    onClick={(e) => {
                      e.preventDefault();
                      setCurrentPage(currentPage + 1);
                    }}
                  >
                    <i className="fas fa-angle-right" />
                    <span className="sr-only">Next</span>
                  </PaginationLink>
                </PaginationItem>
              </Pagination>
            </Card>
          </div>
        </Row>
      </Container>
    </>
  );
};

export default TableServer;
