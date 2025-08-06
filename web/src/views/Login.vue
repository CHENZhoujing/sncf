<template>
  <a-row class="login" justify="center" align="middle">
    <a-col :xs="22" :sm="16" :md="12" :lg="8" :xl="6" class="login-main">
      <h1 style="text-align: center">SNCF Login System</h1>
      <a-form
        :model="loginForm"
        autocomplete="off"
        name="basic"
        @finish="handleLoginSuccess"
        @finishFailed="handleLoginFailed"
      >
        <a-form-item
          :rules="[{ required: true, message: 'Please enter your mobile number!' }]"
          label=""
          name="mobile"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="Mobile Number"/>
        </a-form-item>

        <a-form-item
          :rules="[{ required: true, message: 'Please enter your verification code!' }]"
          label=""
          name="verificationCode"
        >
          <a-input v-model:value="loginForm.verificationCode">
            <template #addonAfter>
              <a @click="sendVerificationCode">Get Code</a>
            </template>
          </a-input>
        </a-form-item>

        <a-form-item>
          <a-button html-type="submit" type="primary">Login</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>
</template>
<script setup>
import {reactive} from 'vue';

const loginForm = reactive({
  mobile: '',
  verificationCode: '',
});
const handleLoginSuccess = values => {
  console.log('Login Success:', values);
};

const handleLoginFailed = errorInfo => {
  console.log('Login Failed:', errorInfo);
};

const sendVerificationCode = () => {
  console.log('Sending verification code to:', loginForm.mobile);
  // TODO: Implement verification code sending logic
};
</script>
<style scoped>
.login {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding: 20px;
}

.login-main {
  background: white;
  border-radius: 12px;
  padding: 40px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.12);
  max-width: 400px;
  width: 100%;
}

.login-main h1 {
  text-align: center;
  margin-bottom: 24px;
  color: #333;
}

.login-main .ant-btn-primary {
  width: 100%;
}
</style>
