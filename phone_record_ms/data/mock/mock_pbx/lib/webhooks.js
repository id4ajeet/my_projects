"use strict";

const axios = require('axios')
const MockCDR = require("./cdr_response")
const Settings = require("./settings")
const Contacts = require("./contacts")
const helpers = require("./helper")

module.exports = {
    init: () => {
        initialize();
        return {status: 'firing webhooks'};
    }
}

const actions = {
    ORIGINATE: 'ORIGINATE',
    RING: 'RING',
    ANSWERED: 'ANSWERED',
    HANGUP: 'HANGUP'
}

let date = new Date();

const initialize = async () => {
    let uuid_ref = helpers.uuidv4();

    let ckeys = Object.keys(Contacts.data);
    let c = Contacts.data[ckeys[ckeys.length * Math.random() << 0]];

    let t = helpers.generate_times(get_latest_ts());
    date = new Date(t.call_end.toUTCString());

    let akeys = Object.keys(actions);
    let action = akeys[akeys.length * Math.random() << 0];

    // Always send ORIGINATE
    await send_originate(uuid_ref, t, c);

    if (action === actions.RING || action === actions.ANSWERED || action === actions.HANGUP)
        await send_ring(uuid_ref, t, c);

    if (action === actions.ANSWERED || action === actions.HANGUP)
        await send_answered(uuid_ref, t, c);

    if (action === actions.HANGUP)
        await send_hangup(uuid_ref, t, c);

}

const get_latest_ts = () => {

    let d;
    if (MockCDR.data.length) {
        let latest_d = MockCDR.data[MockCDR.data.length - 1].call_start;
        d = new Date(latest_d.toUTCString());
        d.setHours(d.getHours() + 1);
    }

    if (d && d > date)
        date = new Date(d.toUTCString());

    return date;
}

const post = async (obj) => {
    const instance = axios.create({
        headers: {'Tenant-UUID': Settings.tenant}
    });

    await instance.post(Settings.webhook_url, obj)
        .then((res) => {
            console.log({response: res.data});
        })
        .catch((error) => {
            console.error(error)
        });
}

const send_originate = async (uuid_ref, t, c) => {
    let originate = {
        uuid: uuid_ref,
        domain_name: Settings.domain,
        caller_name: "",
        caller_number: 35725262136,
        destination_number: c.number,
        direction: "outbound",
        call_start: t.call_start,
        click_to_call: false,
        click_to_call_data: "",
        action: actions.ORIGINATE
    };

    await post(originate);
}

const send_ring = async (uuid_ref, t, c) => {
    let ring = {
        uuid: uuid_ref,
        domain_name: Settings.domain,
        caller_name: "",
        caller_number: 35725262136,
        destination_number: c.number,
        direction: "outbound",
        call_start: t.call_start,
        ring_start: t.ring_start,
        click_to_call: false,
        click_to_call_data: "",
        action: actions.RING
    }

    await post(ring);
}

const send_answered = async (uuid_ref, t, c) => {
    let answered = {
        uuid: uuid_ref,
        domain_name: Settings.domain,
        caller_name: "",
        caller_number: 35725262136,
        destination_number: c.number,
        direction: "outbound",
        call_start: t.call_start,
        ring_start: t.ring_start,
        answer_start: t.answer_start,
        click_to_call: false,
        click_to_call_data: "",
        action: actions.ANSWERED
    }

    await post(answered);
}

const send_hangup = async (uuid_ref, t, c) => {
    let hangup = {
        uuid: uuid_ref,
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
        action: actions.HANGUP
    }

    MockCDR.add_record(hangup);

    await post(hangup);
}
