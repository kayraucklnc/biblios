import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {useEffect, useState} from "react";
import DownloadIcon from '@mui/icons-material/Download';
import "./AdminTabStyle.css";

const token = sessionStorage.getItem("token");


export default function BasicTable() {
  const [rows, setRows] = useState([]);


  useEffect(() => {
    fetch("http://localhost:8080/api/book/borrows", {
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

    function downloadCSV(array, filename) {
        const csvContent = "data:text/csv;charset=utf-8," + array.map(row => Object.values(row).join(',')).join('\n');
        const encodedUri = encodeURI(csvContent);

        const downloadLink = document.createElement('a');
        downloadLink.setAttribute('href', encodedUri);
        downloadLink.setAttribute('download', filename);

        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);
    }

  return (
      <div>
          <DownloadIcon onClick={() => {downloadCSV(rows, "borrows.csv")}} className="download-icon"/>
          <TableContainer component={Paper}>
              <Table sx={{ minWidth: 650 }} aria-label="simple table">
                  <TableHead>
                      <TableRow>
                          <TableCell align="center">Username</TableCell>
                          <TableCell align="center">Bookname</TableCell>
                      </TableRow>
                  </TableHead>
                  <TableBody>
                      {rows.map((row) => (
                          <TableRow
                              key={row.id}
                              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                          >
                              <TableCell component="th" scope="row" align='center'>
                                  {row.username}
                              </TableCell>
                              <TableCell align="center">{row.bookname}</TableCell>
                          </TableRow>
                      ))}
                  </TableBody>
              </Table>
          </TableContainer>
      </div>
  );
}