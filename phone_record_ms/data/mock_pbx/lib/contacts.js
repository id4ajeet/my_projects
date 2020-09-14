"use strict";

const csv = require('csv-parser');
const fs = require('fs');

const data = []

module.exports = {
    data: data,
    load: () => {

        if (data.length)
            return data;

        fs.createReadStream('./pb_directory.csv')
            .pipe(csv())
            .on('data', (row) => {
                row.number = parseInt(row.number);
                data.push(row);
            })
            .on('end', () => {
                console.log('Directory CSV file loaded');
            })
            .on('error', (e) => {
                console.log('Problem loading file: ' + e.message);
            });

        return data;
    }
}
