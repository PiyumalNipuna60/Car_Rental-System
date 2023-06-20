$(function () {
    loadCarDetails();
});

let baseUrl = "http://localhost:8080/Car_Rental_BackEnd_war/";

function loadCarDetails() {
    $('#divGeneral').empty();
    $('#divPremium').empty();
    $('#divLuxury').empty();
    $.ajax({
        url: baseUrl + "api/v1/car",
        method: "GET",
        success: function (res) {
            for (const car of res.data) {
                if (car.type === "General") {

                    let path = car.frontView;
                    console.log(path)
                    let img = path.split("H:\\Github Projects\\Easy-Car-Rental\\Car-Rental-FontEnd\\assets\\savedImages\\Cars\\")[1];
                    console.log(img)
                    let imgSrc = "assets\\savedImages\\Cars\\" + img;

                    let div = `<div class="col" id="card10" data-aos="fade-up" data-aos-anchor-placement="top-bottom"
                     data-aos-delay="500">
                    <div class="card h-100" style="border-radius: 25px 0 25px 0;border: 2px solid gray">
                        <img src=${imgSrc} class="card-img-top" alt="..."
                             style="border-radius: 25px 0 25px 0">
                        <div class="card-body text-center">
                            <h5 class="card-title">${car.brand}</h5>
                            <h6 class="card-header text-muted">Free Km for a Day- ${car.freeKmForDuration}
                            </h6>
                            <h6 class="pricePerKm">Price per Extra Km - ${car.priceForExtraKm}</h6>
                        </div>
                        <div class="lineL"></div>
                        <div class="card-footer">
                            <div class="row">

                                <div class="col"><img src="assets/images/transmission.png" style="width: 17px"><span
                                        class="cardFooth6"> ${car.transmissionType}</span></div>
                                <div class="col"><img src="assets/images/fuel.png" alt="" style="width: 17px"><span
                                        class="cardFooth6"> ${car.fuelType}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`;

                    $('#divGeneral').append(div);

                } else if (car.type === "Premium"){
                    let path = car.frontView;
                    let img = path.split("H:\\Github Projects\\Easy-Car-Rental\\Car-Rental-FontEnd\\assets\\savedImages\\Cars\\")[1];
                    let imgSrc = "assets\\savedImages\\Cars\\" + img;

                    let div = `<div class="col" id="card10" data-aos="fade-up" data-aos-anchor-placement="top-bottom"
                     data-aos-delay="500">
                    <div class="card h-100" style="border-radius: 25px 0 25px 0;border: 2px solid gray">
                        <img src=${imgSrc} class="card-img-top" alt="..."
                             style="border-radius: 25px 0 25px 0">
                        <div class="card-body text-center">
                            <h5 class="card-title">${car.brand}</h5>
                            <h6 class="card-header text-muted">Free Km for a Day- ${car.freeKmForDuration}
                            </h6>
                            <h6 class="pricePerKm">Price per Extra Km - ${car.priceForExtraKm}</h6>
                        </div>
                        <div class="lineL"></div>
                        <div class="card-footer">
                            <div class="row">

                                <div class="col"><img src="assets/images/transmission.png" style="width: 17px"><span
                                        class="cardFooth6"> ${car.transmissionType}</span></div>
                                <div class="col"><img src="assets/images/fuel.png" alt="" style="width: 17px"><span
                                        class="cardFooth6"> ${car.fuelType}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`;

                    $('#divPremium').append(div);

                } else if (car.type === "Luxury"){
                    let path = car.frontView;
                    let img = path.split("H:\\Github Projects\\Easy-Car-Rental\\Car-Rental-FontEnd\\assets\\savedImages\\Cars\\")[1];
                    let imgSrc = "assets\\savedImages\\Cars\\" + img;

                    let div = `<div class="col" id="card10" data-aos="fade-up" data-aos-anchor-placement="top-bottom"
                     data-aos-delay="500">
                    <div class="card h-100" style="border-radius: 25px 0 25px 0;border: 2px solid gray">
                        <img src=${imgSrc} class="card-img-top" alt="..."
                             style="border-radius: 25px 0 25px 0">
                        <div class="card-body text-center">
                            <h5 class="card-title">${car.brand}</h5>
                            <h6 class="card-header text-muted">Free Km for a Day- ${car.freeKmForDuration}
                            </h6>
                            <h6 class="pricePerKm">Price per Extra Km - ${car.priceForExtraKm}</h6>
                        </div>
                        <div class="lineL"></div>
                        <div class="card-footer">
                            <div class="row">

                                <div class="col"><img src="assets/images/transmission.png" style="width: 17px"><span
                                        class="cardFooth6"> ${car.transmissionType}</span></div>
                                <div class="col"><img src="assets/images/fuel.png" alt="" style="width: 17px"><span
                                        class="cardFooth6"> ${car.fuelType}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`;

                    $('#divLuxury').append(div);
                }
            }
        }
    })
}