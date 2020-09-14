"use strict";

const Hapi = require("@hapi/hapi");
const Settings = require("./lib/settings");
const Routes = require("./lib/routes");
const Contacts = require("./lib/contacts")
const Helper = require("./lib/helper")

const init = async () => {
    Settings.tenant = Helper.uuidv4();
    await Contacts.load();

    const server = new Hapi.Server({port: Settings.port});

    server.route(Routes);

    await server.start();
    console.log(`Mock server running at: ${server.info.uri}`);
};

process.on("unhandledRejection", err => {
    console.log(err);
    process.exit(1);
});

init();
