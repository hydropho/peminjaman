import { Validator } from "vee-validate";

Validator.extend("timeAfter", {
  getMessage: (field, [refTime]) => `The ${field} must be after ${refTime}.`,
  validate: (value, ref) => {
    if (!value) {
      return false;
    }

    if ((ref[2] as string) === "") {
      return false;
    }

    if (ref[1] === ref[2]) {
      let refTime = ref[0].split(":");

      const hourPlusOne = parseInt(refTime[0]) + 1;

      refTime[0] = hourPlusOne.toString();

      const newRefTime = refTime.join(":");

      return value > newRefTime;
    }

    return true;
  },
});
