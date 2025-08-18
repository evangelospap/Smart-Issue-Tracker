import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  async rewrites() {
    return [
      {
        source: '/api/:path*',        // frontend URL
        destination: 'http://localhost:8081/api/:path*', // backend URL
      },
    ];
  },
};

export default nextConfig;
