"use strict";

const Settings = require("./settings")
const helpers = require("./helper")
const Contacts = require("./contacts")

const cdr_data = [];

module.exports = {
    data: cdr_data,
    response: () => {
        return {
            total: cdr_data.length,
            data: cdr_data
        };
    },
    add_record: (r) => {
        cdr_data.push(r);
    },
    generate_data: (amount = 5) => {

        let d = new Date();
        if (cdr_data.length) {
            let latest_d = cdr_data[cdr_data.length - 1].call_start;
            d = new Date(latest_d.toUTCString());
        }

        for (let c = 0; c < amount; c++) {
            d.setHours(d.getHours() + 1);
            let cdr = generate_record(d);
            cdr_data.push(cdr)
        }
    }
}

const generate_record = (d) => {
    let keys = Object.keys(Contacts.data);
    let c = Contacts.data[keys[keys.length * Math.random() << 0]];

    let t = helpers.generate_times(d);

    return {
        uuid: helpers.uuidv4(),
        domain_name: Settings.domain,
        caller_name: "",
        caller_number: 35725262136,
        destination_number: c.number,
        direction: "outbound",
        call_start: t.call_start,
        ring_start: t.ring_start,
        answer_start: t.answer_start,
        call_end: t.call_end,
        duration: t.duration,
        recording: helpers.uuidv4(),
        click_to_call: false,
        click_to_call_data: "",
        action: "HANGUP"
    };
}
