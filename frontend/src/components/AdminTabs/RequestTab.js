import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {useEffect, useState} from "react";

const token = sessionStorage.getItem("token");

function createData(
    name,
    calories,
    fat,
    carbs,
    protein,
) {
  return { name, calories, fat, carbs, protein };
}

// const rows = [
//   createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
//   createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
//   createData('Eclair', 262, 16.0, 24, 6.0),
//   createData('Cupcake', 305, 3.7, 67, 4.3),
//   createData('Gingerbread', 356, 16.0, 49, 3.9),
// ];


export default function BasicTable() {
  const [rows, setRows] = useState([]);


  useEffect(() => {
    fetch("http://localhost:8080/api/log/", {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log(data)
          setRows(data);
          return data;
        })
        .catch((error) => {
          console.error("There's an error", error);
        });
  }, []);

  return (
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell align="right">ID</TableCell>
              <TableCell align="right">Timestamp</TableCell>
              <TableCell align="right">Log</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
                <TableRow
                    key={row.id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {row.id}
                  </TableCell>
                  <TableCell align="right">{row.timestamp}</TableCell>
                  <TableCell align="right">{row.log}</TableCell>
                </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
  );
}