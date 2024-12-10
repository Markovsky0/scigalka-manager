const PROXY_CONFIG = [
  {
    context: [
      '/auth/*'
    ],
    target: "http://localhost:8080",
    secure: false,
    timeout: 20 * 60 * 1000
  }
]

module.exports = PROXY_CONFIG
