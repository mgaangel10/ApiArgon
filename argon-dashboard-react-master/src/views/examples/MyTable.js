import React, { useState } from "react";
import {
  Badge,
  Card,
  CardHeader,
  Table,
  Container,
  Row,
  Button,
  Input,
  Pagination,
  PaginationItem,
  PaginationLink,
  Modal,
  ModalHeader,
  ModalBody,
} from "reactstrap";
import { FaSort, FaSortUp, FaSortDown } from "react-icons/fa"; 
import Header from "components/Headers/Header.js";
import dataTable from "../../models/MyJson"; 

const MyTable = () => {
  const [data, setData] = useState(dataTable); 
  const [buscarQuery, setBuscarQuery] = useState(""); // para la busqueda
  const [filtrarDatos, setFiltrarDatos] = useState(dataTable); // para filtrar los datos mayor que ...
  const [currentPage, setCurrentPage] = useState(1); // pagina actual
  const resultadosPorPagina = 4; // resultados por pagina 
  const [isOrdenado, setIsOrdenado] = useState({
    name: false,
    age: false,
    startDate: false,
    salary: false,
  }); // es el estado de la ordenacion



  const [modal, setModal] = useState(false); // modal
  const [seleccionarEmpleado, setSeleccionarEmpleado] = useState(null); // elemento seleccionado

  // algoritmo para ordenar, nombre , age, date,salary
  const ordenar = (key, type = "string") => {
    const sortedData = [...filtrarDatos].sort((a, b) => {
      if (type === "string") {
        return a[key].localeCompare(b[key]);
      } else if (type === "number") {
        return parseFloat(a[key]) - parseFloat(b[key]);
      } else if (type === "date") {
        return new Date(a[key]) - new Date(b[key]);
      }
      return 0;
    });

    // cambiar por orden ascendente y descendente
    setFiltrarDatos(isOrdenado[key] ? sortedData.reverse() : sortedData);
    setIsOrdenado((prev) => ({ ...prev, [key]: !prev[key] }));
  };

  // menjar la busqueda
  const handleSearch = (event) => {
    const query = event.target.value.toLowerCase();
    setBuscarQuery(query);

    const filtrados = data.filter(
      (item) =>
        item.name.toLowerCase().includes(query) ||
        item.position.toLowerCase().includes(query) ||
        item.office.toLowerCase().includes(query)
    );
    setFiltrarDatos(filtrados);
    setCurrentPage(1); 
  };

  // cambiar de pagina
  const cambiarPagina = (nuevaPagina) => {
    setCurrentPage(nuevaPagina);
  };

  // abrir el modal
  const abrirModal = (item) => {
    setSeleccionarEmpleado(item);
    setModal(true);
  };

  // cerrar el modal
  const closeModal = () => {
    setSeleccionarEmpleado(null);
    setModal(false);
  };

  // calcular paginacion
  const indexOfLastResult = currentPage * resultadosPorPagina;
  const indexOfFirstResult = indexOfLastResult - resultadosPorPagina;
  const currentResults = filtrarDatos.slice(indexOfFirstResult, indexOfLastResult);

  // total de páginas
  const totalPages = Math.ceil(filtrarDatos.length / resultadosPorPagina);

  return (
    <>
      <Header />
      <Container className="mt--7" fluid>
        <Row>
          <div className="col">
            <Card className="shadow">
              <CardHeader className="border-0">
                <h3 className="mb-0">Employee Data Table</h3>
                <Input
                  type="text"
                  placeholder="Search by Name, Position, or Office"
                  value={buscarQuery}
                  onChange={handleSearch}
                  style={{ marginTop: "10px", maxWidth: "300px" }}
                  className="ml-auto"
                />
              </CardHeader>

              <Table className="align-items-center table-flush" responsive>
                <thead className="thead-light">
                  <tr>
                    <th scope="col">
                      Name{" "}
                      <Button
                        size="sm"
                        color="primary"
                        onClick={() => ordenar("name")}
                        style={{ marginLeft: "10px" }}
                      >
                        {isOrdenado.name ? <FaSortUp /> : <FaSortDown />}
                      </Button>
                    </th>
                    <th scope="col">Position</th>
                    <th scope="col">Office</th>
                    <th scope="col">
                      Age{" "}
                      <Button
                        size="sm"
                        color="primary"
                        onClick={() => ordenar("age", "number")}
                        style={{ marginLeft: "10px" }}
                      >
                        {isOrdenado.age ? <FaSortUp /> : <FaSortDown />}
                      </Button>
                    </th>
                    <th scope="col">
                      Start Date{" "}
                      <Button
                        size="sm"
                        color="primary"
                        onClick={() => ordenar("start_date", "date")}
                        style={{ marginLeft: "10px" }}
                      >
                        {isOrdenado.startDate ? <FaSortUp /> : <FaSortDown />}
                      </Button>
                    </th>
                    <th scope="col">
                      Salary{" "}
                      <Button
                        size="sm"
                        color="primary"
                        onClick={() => ordenar("salary", "number")}
                        style={{ marginLeft: "10px" }}
                      >
                        {isOrdenado.salary ? <FaSortUp /> : <FaSortDown />}
                      </Button>
                    </th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody>
                  {currentResults.map((item, index) => (
                    <tr key={index}>
                      <td>{item.name}</td>
                      <td>{item.position}</td>
                      <td>{item.office}</td>
                      <td>{item.age}</td>
                      <td>{item.start_date}</td>
                      <td>{item.salary}</td>
                      <td>
                        <Button color="info" size="sm" onClick={() => abrirModal(item)}>
                          Open Modal
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>

              
              <nav aria-label="Page navigation">
                <Pagination className="pagination justify-content-end mb-0">
                  <PaginationItem className={currentPage === 1 ? "disabled" : ""}>
                    <PaginationLink
                      href="#"
                      onClick={(e) => {
                        e.preventDefault();
                        cambiarPagina(currentPage - 1);
                      }}
                    >
                      <i className="fas fa-angle-left" />
                    </PaginationLink>
                  </PaginationItem>
                  {[...Array(totalPages)].map((_, index) => (
                    <PaginationItem
                      key={index}
                      className={currentPage === index + 1 ? "active" : ""}
                    >
                      <PaginationLink
                        href="#"
                        onClick={(e) => {
                          e.preventDefault();
                          cambiarPagina(index + 1);
                        }}
                      >
                        {index + 1}
                      </PaginationLink>
                    </PaginationItem>
                  ))}
                  <PaginationItem className={currentPage === totalPages ? "disabled" : ""}>
                    <PaginationLink
                      href="#"
                      onClick={(e) => {
                        e.preventDefault();
                        cambiarPagina(currentPage + 1);
                      }}
                    >
                      <i className="fas fa-angle-right" />
                    </PaginationLink>
                  </PaginationItem>
                </Pagination>
              </nav>
            </Card>
          </div>
        </Row>

        
        {seleccionarEmpleado && (
          <Modal isOpen={modal} toggle={closeModal}>
            <ModalHeader toggle={closeModal}>Employee Details</ModalHeader>
            <ModalBody>
              <p><strong>Name:</strong> {seleccionarEmpleado.name}</p>
              <p><strong>Position:</strong> {seleccionarEmpleado.position}</p>
              <p><strong>Office:</strong> {seleccionarEmpleado.office}</p>
              <p><strong>Age:</strong> {seleccionarEmpleado.age}</p>
              <p><strong>Start Date:</strong> {seleccionarEmpleado.start_date}</p>
              <p><strong>Salary:</strong> {seleccionarEmpleado.salary}</p>
            </ModalBody>
          </Modal>
        )}
      </Container>
    </>
  );
};

export default MyTable;
