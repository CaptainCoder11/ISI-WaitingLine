import * as moment from 'moment';

export const DISPLAY_LOGO = (logo) => `data:image/png;base64,${logo}`;

export const DISPLAY_STORE_STATUS = (openingHour, closingHour) => {
    var openingHourStr = openingHour.split(':');
    var openingHourDate = new Date().setHours(parseInt(openingHourStr[0]), parseInt(openingHourStr[1]), parseInt(openingHourStr[2]));
    var openingTime = moment(openingHourDate).format("HH:mm:ss");

    var closingHourStr = closingHour.split(':');
    var closingHourDate = new Date().setHours(parseInt(closingHourStr[0]), parseInt(closingHourStr[1]), parseInt(closingHourStr[2]));
    var closingTime = moment(closingHourDate).format("HH:mm:ss");

    var todayDate = new Date();
    var todayTime = moment(todayDate).format("HH:mm:ss");

    if (todayTime >= openingTime && todayTime < closingTime) {
        return "Store is open";
    }
    return "Store is closed";
}