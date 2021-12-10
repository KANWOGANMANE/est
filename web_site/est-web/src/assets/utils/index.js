/**
 * 计时器
 * @param {Number} totalTime  起始时间(秒)
 * @param {*} fn 赋值方法
 * @param {*} endFn 结束动作
 */
export function countDown(totalTime, fn, endFn) {
  let time = null;
  time = setInterval(() => {
    totalTime--;
    fn(totalTime);
    if (!totalTime) {
      endFn();
      clearInterval(time);
    }
  }, 1000);
}

export function getUserData() {
  return {
    token: sessionStorage.token || undefined,
    overdueTime: sessionStorage.overdueTime || undefined,
    userInfo: sessionStorage.userInfo || undefined
  };
}

export function setUserData({ token, userInfo }) {
  sessionStorage.token = token;
  sessionStorage.userInfo = userInfo;
  sessionStorage.overdueTime = +new Date() + 30 * 60 * 1000;
}

export function removeUserData() {
  sessionStorage.removeItem("token");
  sessionStorage.removeItem("userInfo");
  sessionStorage.removeItem("overdueTime");
}

export function dateList(limit = 7) {
  //上午8到10点，中午10到14点,下午14到16点，晚上16到20点
  let timeQuantumList = [
    "上午(08:00-10:00)",
    "中午(10:00-14:00)",
    "下午(14:00-16:00)",
    "晚上(16:00-20:00)"
  ];
  let week = ["日", "一", "二", "三", "四", "五", "六"];
  let currentYear = new Date().getFullYear();
  let currentMonth = new Date().getMonth();
  let currentDay = new Date().getDate();
  // let currentWeek = new Date().getDay();
  let currentHours = new Date().getHours();
  let output = [];
  function handleDate(day) {
    return new Date(currentYear, currentMonth, currentDay + day);
  }

  function handleItem(index, i) {
    return {
      year: handleDate(index).getFullYear(),
      month: handleDate(index).getMonth() + 1,
      day: handleDate(index).getDate(),
      week: "(周" + week[handleDate(index).getDay()] + ")",
      timeQuantum: timeQuantumList[i],
      temporalInterval: i + 1 //预约时间区间
    };
  }

  for (let index = 0; index < limit; index++) {
    for (let i = 0; i < 4; i++) {
      output.push(handleItem(index, i));
    }
  }

  //起始位置
  let sliceStart = 0;
  switch (true) {
    case currentHours >= 20:
      sliceStart = 5;
      break;
    case currentHours >= 16:
      sliceStart = 4;
      break;
    case currentHours >= 14:
      sliceStart = 3;
      break;
    case currentHours >= 10:
      sliceStart = 2;
      break;
    case currentHours >= 8:
      sliceStart = 1;
      break;
  }

  return output.slice(sliceStart);
}

export function validateRequire(rule, value, callback) {
  if (value === "") {
    callback(new Error("不能为空"));
  }
  callback();
}

export function validatePhone(rule, value, callback) {
  let reg = /(13[0-9]|14[579]|15[0-35-9]|16[6]|17[0135678]|18[0-9]|19[89])[0-9]{8}$/g;
  if (value === "") {
    callback(new Error("不能为空"));
  } else if (!this.loginMode && !reg.test(value)) {
    callback(new Error("请输入正确手机号"));
  } else if (!this.loginMode && value.length !== 11) {
    callback(new Error("请输入正确手机号"));
  } else {
    this.getSmsButton = false;
  }
  callback();
}
