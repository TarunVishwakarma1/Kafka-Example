const {kafka} = require("./client");




async function init(){
    const admin = kafka.admin();
    console.log('Admin Connecting...')
    admin.connect();
    console.log('Admin Connecting success....')

    console.log('Creating Topics [rider-updates]');

    await admin.createTopics({
        topics:[{
            topic:'rider-updates',
            numPartitions:2,
        }],
    });

    console.log('Topic created success...');

    console.log('Admin Disconnecting')
    await admin.disconnect();
    console.log('Admin Disconnected Success')
}

init();