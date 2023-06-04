import React, {useState, useEffect} from "react";
import {styled} from "@mui/material/styles";
import "./AdminTabStyle.css";

import {DataGrid} from "@mui/x-data-grid";

import {Box} from "@mui/material";
import {Block, HowToReg} from "@mui/icons-material";
import DownloadIcon from "@mui/icons-material/Download";

const ApproveUsersTab = ({setPingUserList, pingUserList}) => {
    const [rows, setRows] = useState([]);
    const [finalizedRows, setFinalizedRows] = useState([]);
    const [loading, setLoading] = useState(true);
    const [pageSize, setPageSize] = useState(10);
    const [selectedRows, setSelectedRows] = useState([]);
    const [pingFetch, setPingFetch] = useState(false);

    const token = sessionStorage.getItem("token");

    const setTableHeight = (i) => {
        document.getElementById("approveUsersTable").style.height = `${
            (i === 0 ? 400 : 109) + 52 * Math.min(i, pageSize)
        }px`;
        document.querySelector(
            "#approveUsersTable > div.MuiDataGrid-root.css-1xy1myn-MuiDataGrid-root > div:nth-child(3) > div > div.MuiTablePagination-root.css-rtrcn9-MuiTablePagination-root > div > p"
        ).style.width = "110px";
    };

    useEffect(() => {
        let newRows = [];

        for (const row of rows) {
            let newRow = {};

            newRow.id = row.isbn;
            newRow.name = row.name;
            newRow.format = row.format;
            newRow.author = row.author;

            newRows.push(newRow);
        }
        setFinalizedRows(newRows);
    }, [rows]);

    useEffect(() => {
        let approveBtn = document.getElementById("approveUserButton");
        let denyBtn = document.getElementById("denyUserButton");
        if (approveBtn && denyBtn) {
            if (selectedRows.length > 0) {
                approveBtn.classList.add("manageButtonsActive");
                denyBtn.classList.add("manageButtonsActive");
            } else {
                approveBtn.classList.remove("manageButtonsActive");
                denyBtn.classList.remove("manageButtonsActive");
            }
        }
    }, [selectedRows]);

    const handleApproveUser = () => {
        return Promise.all(
            selectedRows.map((username) => {
                return fetch(`http://localhost:8080/api/signup/approve`, {
                    method: "POST",
                    headers: {
                        "Content-type": "application/json",
                        Authorization: `Bearer ${token}`,
                    },
                    body: JSON.stringify({
                        username: username,
                    }),
                })
                    .then((resp) => resp)
                    .then(() => {
                        setPingUserList(!pingUserList);
                    });
            })
        );
    };

    const handleDenyUser = () => {
        return Promise.all(selectedRows.map((bookISBN) => fetchDeny(bookISBN)));
    };

    const fetchDeny = (bookISBN) => {
        const opts = {
            method: "POST",
            headers: {
                "Content-type": "application/json",
                Authorization: `Bearer ${token}`,
            },
        };

        return fetch(`http://localhost:8080/api/book/delete/` + bookISBN, opts);
    };

    useEffect(() => {
        setLoading(true);
        fetch("http://localhost:8080/api/book/", {
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
                setRows(data);
                setTableHeight(data.length);
                setLoading(false);
                return data;
            })
            .catch((error) => {
                console.error("There's an error", error);
            });
    }, [pingFetch]);

    const columns = [
        {field: "id", headerName: "ID", flex: 1},
        {field: "name", headerName: "Book name", flex: 3},
        {field: "format", headerName: "Format", flex: 1},
        {field: "author", headerName: "Author", flex: 1},
    ];

    const StyledGridOverlay = styled("div")(({theme}) => ({
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        height: "100%",
        "& .ant-empty-img-1": {
            fill: theme.palette.mode === "light" ? "#aeb8c2" : "#262626",
        },
        "& .ant-empty-img-2": {
            fill: theme.palette.mode === "light" ? "#f5f5f7" : "#595959",
        },
        "& .ant-empty-img-3": {
            fill: theme.palette.mode === "light" ? "#dce0e6" : "#434343",
        },
        "& .ant-empty-img-4": {
            fill: theme.palette.mode === "light" ? "#fff" : "#1c1c1c",
        },
        "& .ant-empty-img-5": {
            fillOpacity: theme.palette.mode === "light" ? "0.8" : "0.08",
            fill: theme.palette.mode === "light" ? "#f5f5f5" : "#fff",
        },
    }));

    const CustomNoRowsOverlay = () => {
        return (
            <StyledGridOverlay>
                <svg
                    width="240"
                    height="200"
                    viewBox="0 0 184 152"
                    aria-hidden
                    focusable="false"
                >
                    <g fill="none" fillRule="evenodd">
                        <g transform="translate(24 31.67)">
                            <ellipse
                                className="ant-empty-img-5"
                                cx="67.797"
                                cy="106.89"
                                rx="67.797"
                                ry="12.668"
                            />
                            <path
                                className="ant-empty-img-1"
                                d="M122.034 69.674L98.109 40.229c-1.148-1.386-2.826-2.225-4.593-2.225h-51.44c-1.766 0-3.444.839-4.592 2.225L13.56 69.674v15.383h108.475V69.674z"
                            />
                            <path
                                className="ant-empty-img-2"
                                d="M33.83 0h67.933a4 4 0 0 1 4 4v93.344a4 4 0 0 1-4 4H33.83a4 4 0 0 1-4-4V4a4 4 0 0 1 4-4z"
                            />
                            <path
                                className="ant-empty-img-3"
                                d="M42.678 9.953h50.237a2 2 0 0 1 2 2V36.91a2 2 0 0 1-2 2H42.678a2 2 0 0 1-2-2V11.953a2 2 0 0 1 2-2zM42.94 49.767h49.713a2.262 2.262 0 1 1 0 4.524H42.94a2.262 2.262 0 0 1 0-4.524zM42.94 61.53h49.713a2.262 2.262 0 1 1 0 4.525H42.94a2.262 2.262 0 0 1 0-4.525zM121.813 105.032c-.775 3.071-3.497 5.36-6.735 5.36H20.515c-3.238 0-5.96-2.29-6.734-5.36a7.309 7.309 0 0 1-.222-1.79V69.675h26.318c2.907 0 5.25 2.448 5.25 5.42v.04c0 2.971 2.37 5.37 5.277 5.37h34.785c2.907 0 5.277-2.421 5.277-5.393V75.1c0-2.972 2.343-5.426 5.25-5.426h26.318v33.569c0 .617-.077 1.216-.221 1.789z"
                            />
                        </g>
                        <path
                            className="ant-empty-img-3"
                            d="M149.121 33.292l-6.83 2.65a1 1 0 0 1-1.317-1.23l1.937-6.207c-2.589-2.944-4.109-6.534-4.109-10.408C138.802 8.102 148.92 0 161.402 0 173.881 0 184 8.102 184 18.097c0 9.995-10.118 18.097-22.599 18.097-4.528 0-8.744-1.066-12.28-2.902z"
                        />
                        <g className="ant-empty-img-4" transform="translate(149.65 15.383)">
                            <ellipse cx="20.654" cy="3.167" rx="2.849" ry="2.815"/>
                            <path d="M5.698 5.63H0L2.898.704zM9.259.704h4.985V5.63H9.259z"/>
                        </g>
                    </g>
                </svg>
                <Box sx={{mt: 1, fontSize: 20}}>No Pending Users</Box>
            </StyledGridOverlay>
        );
    };
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
            <DownloadIcon onClick={() => {downloadCSV(rows, "books.csv")}} className="download-icon"/>
            <div id="approveUsersTable" className="approveUsersTable">
                <div style={{display: "flex", flexDirection: "column"}}>
                    <div
                        className="manageButtonsPassive"
                        id="approveUserButton"
                        onClick={() => {
                            handleApproveUser().then(() => setPingFetch(!pingFetch));
                        }}
                    >
                        {" "}
                        {/*<HowToReg style={{fontSize: 40, marginRight: 5}}/> Approve Users{" "}*/}
                    </div>
                    <div
                        className="manageButtonsPassive"
                        id="denyUserButton"
                        onClick={() => {
                            handleDenyUser().then(() => setPingFetch(!pingFetch));
                        }}
                    >
                        {" "}
                        <Block style={{fontSize: 40, marginRight: 5}}/> Delete Book{" "}
                    </div>
                </div>
                <DataGrid
                    loading={loading}
                    rows={finalizedRows}
                    columns={columns}
                    pageSize={pageSize}
                    rowsPerPageOptions={[pageSize]}
                    checkboxSelection
                    onSelectionModelChange={(items) => setSelectedRows(items)}
                    components={{
                        NoRowsOverlay: CustomNoRowsOverlay,
                    }}
                />
            </div>
        </div>
    );
};

export default ApproveUsersTab;
